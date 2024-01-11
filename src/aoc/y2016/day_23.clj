(ns aoc.y2016.day-23)

(def ^:private instructions
  {:cpy (fn [vm x r] (assoc vm
                         r (if-let [v (get vm x)] v x)
                         :ip (inc (:ip vm))))

   :inc (fn [vm r] (assoc vm
                     r (inc (get vm r))
                     :ip (inc (:ip vm))))

   :dec (fn [vm r] (assoc vm
                     r (dec (get vm r))
                     :ip (inc (:ip vm))))

   :mul (fn [vm x y r] (assoc vm
                         r (* (get vm x) (get vm y))
                         :ip (inc (:ip vm))))

   :jnz (fn [vm x offset] (assoc vm :ip (if-not (zero? (if-let [v (get vm x)] v x))
                                          (+ (:ip vm) (if (keyword? offset) (get vm offset) offset))
                                          (inc (:ip vm)))))

   :tgl (fn [vm x code]
          (let [xip (+ (:ip vm) (get vm x))]
            (if (>= xip (count code))
              {:vm (assoc vm :ip (inc (:ip vm)))
               :code code}
              (let [{:keys [op args]} (nth code xip)
                    new-op (cond
                             (= 1 (count args))
                             (if (= :inc op) :dec :inc)

                             (= 2 (count args))
                             (if (= :jnz op) :cpy :jnz))]
                {:vm (assoc vm :ip (inc (:ip vm)))
                 :code (vec (concat (take xip code) [{:op new-op :args args}] (drop (inc xip) code)))}))))})

(defn- init-program
  [code]
  {:vm {:a 0 :b 0 :c 0 :d 0 :ip 0} :code code})

(defn- eval-next
  [program]
  (let [vm (:vm program)
        ip (:ip vm)
        ins (nth (:code program) ip)
        op (:op ins)
        args (:args ins)
        f (partial (get instructions op) vm)]
    (if (= :tgl op)
      (let [{:keys [vm code]} (apply f (concat args [(:code program)]))]
        (assoc program :vm vm :code code))
      (let [vm (apply f args)]
        (assoc program :vm vm)))))

(defn- parse-line
  [l]
  (let [[op args] (clojure.string/split l #"\s" 2)
        args (->> (clojure.string/split args #" ")
                  (map (fn [arg]
                         (cond
                           (re-find #"\d" arg) (Integer/parseInt arg)
                           :else (keyword arg)))))]
    {:op (keyword op) :args args}))

(defn part-1
  [input]
  (let [evaluated (->> input
                       (map parse-line)
                       vec
                       init-program
                       (#(assoc-in % [:vm :a] 7))
                       (iterate eval-next)
                       (drop-while #(< (get-in % [:vm :ip]) (count input)))
                       first)]
    (get-in evaluated [:vm :a])))

(defn part-2
  [input]
  (let [evaluated (->> input
                       (map parse-line)
                       vec
                       init-program
                       (#(assoc-in % [:vm :a] 12))
                       (iterate eval-next)
                       (drop-while #(< (get-in % [:vm :ip]) (count input)))
                       first)]
    (get-in evaluated [:vm :a])))