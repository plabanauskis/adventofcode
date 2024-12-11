(ns aoc.y2024.day-7
  (:require [clojure.math.combinatorics :as comb]))

(defn- parse-line
  [line]
  (let [[result & operands] (re-seq #"\d+" line)]
    [(parse-long result) (mapv parse-long operands)]))

(defn- valid?
  [allowed-operations [result operands]]
  (let [operations (comb/selections allowed-operations
                                    (dec (count operands)))]
    (some (fn [ops]
            (= result
               (reduce (fn [acc [operation operand]]
                         (operation acc operand))
                       (first operands)
                       (map vector ops (drop 1 operands)))))
          operations)))

(defn- get-calibration-result
  [input allowed-operations]
  (->> input
       (map parse-line)
       (filter (partial valid? allowed-operations))
       (map first)
       (apply +)))

(defn part-1
  [input]
  (get-calibration-result input [+ *]))

(defn- ||
  [a b]
  (parse-long (str a b)))

(defn part-2
  [input]
  (get-calibration-result input [+ * ||]))
