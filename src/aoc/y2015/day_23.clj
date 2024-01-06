(ns aoc.y2015.day-23
  (:require [clojure.string :as str]))

(def ^:dynamic resulting-register :b)

(def ^:private instructions
  {:hlf (fn [vm r] (assoc vm
                     r (quot (get vm r) 2)
                     :ip (inc (:ip vm))))

   :tpl (fn [vm r] (assoc vm
                     r (* (get vm r) 3)
                     :ip (inc (:ip vm))))

   :inc (fn [vm r] (assoc vm r
                             (inc (get vm r))
                             :ip (inc (:ip vm))))

   :jmp (fn [vm offset] (assoc vm :ip (+ (:ip vm) offset)))

   :jie (fn [vm r offset] (assoc vm :ip (if (zero? (mod (get vm r) 2))
                                          (+ (:ip vm) offset)
                                          (inc (:ip vm)))))
   
   :jio (fn [vm r offset] (assoc vm :ip (if (= 1 (get vm r))
                                          (+ (:ip vm) offset)
                                          (inc (:ip vm)))))})

(defn- init-program
  [code]
  {:vm {:a 0 :b 0 :ip 0} :code code})

(defn- eval-next
  [program]
  (let [vm (:vm program)
        ip (:ip vm)
        ins (nth (:code program) ip)
        op (:op ins)
        args (:args ins)
        f (partial (get instructions op) vm)]
    (assoc program :vm (apply f args))))

(defn- parse-line
  [l]
  (let [[op args] (str/split l #"\s" 2)
        args (->> (str/split args #", ")
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
                       (iterate eval-next)
                       (drop-while #(< (get-in % [:vm :ip]) (count input)))
                       first)]
    (get-in evaluated [:vm resulting-register])))

(defn part-2
  [input]
  (let [evaluated (->> input
                       (map parse-line)
                       vec
                       init-program
                       (#(assoc-in % [:vm :a] 1))
                       (iterate eval-next)
                       (drop-while #(< (get-in % [:vm :ip]) (count input)))
                       first)]
    (get-in evaluated [:vm resulting-register])))