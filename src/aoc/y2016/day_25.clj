(ns aoc.y2016.day-25)

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

   :jnz (fn [vm x offset] (assoc vm :ip (if-not (zero? (if-let [v (get vm x)] v x))
                                          (+ (:ip vm) (if (keyword? offset) (get vm offset) offset))
                                          (inc (:ip vm)))))

   :out (fn [vm x]
          (assoc vm
                     :out (conj (:out vm) (if-let [v (get vm x)] v x))
                     :ip (inc (:ip vm))))})

(defn- init-program
  [code]
  {:vm {:a 0 :b 0 :c 0 :d 0 :ip 0 :ins 0 :out []} :code code})

(defn- read-output
  [vm]
  {:out (:out vm)
   :vm (assoc vm :out nil)})

(defn- eval-next
  [program]
  (let [vm (:vm program)
        ip (:ip vm)
        ins (nth (:code program) ip)
        op (:op ins)
        args (:args ins)
        f (partial (get instructions op) vm)
        program (assoc program :vm (apply f args))]
    (update-in program [:vm :ins] inc)))

(defn- parse-line
  [l]
  (let [[op args] (clojure.string/split l #"\s" 2)
        args (->> (clojure.string/split args #" ")
                  (map (fn [arg]
                         (cond
                           (re-find #"\d" arg) (Integer/parseInt arg)
                           :else (keyword arg)))))]
    {:op (keyword op) :args args}))

(defn- signal-repeating-forever?
  [code input]
  (letfn [(correct-output? [vm]
            (let [output (get-in vm [:vm :out])]
              (= output
                 (take (count output) (cycle [0 1])))))]
    (->> code
         init-program
         (#(assoc-in % [:vm :a] input))
         (iterate eval-next)
         (take-while correct-output?)
         (take-while #(< (get-in % [:vm :ins]) 1000000))
         last
         eval-next
         correct-output?)))

(defn part-1
  [input]
  (let [code (->> input
                  (map parse-line)
                  vec)]
    (->> (iterate inc 1)
         (drop-while (complement (partial signal-repeating-forever? code)))
         first)))

(defn part-2
  [input])