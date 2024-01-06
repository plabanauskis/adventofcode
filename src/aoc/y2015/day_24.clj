(ns aoc.y2015.day-24
  (:require [clojure.math.combinatorics :as comb]))

(defn- balance-sleigh
  [weights group-count]
  (let [weight (/ (apply + weights) group-count)
        items (->> (comb/subsets weights)
                   (drop-while #(not= weight (apply + %))))
        item-count (count (first items))
        qe #(reduce * 1 %)]
    (->> items
         (take-while #(= item-count (count %)))
         (filter #(= weight (apply + %)))
         (sort-by qe)
         first
         qe)))

(defn part-1
  [input]
  (balance-sleigh (map #(Integer/parseInt %) input) 3))

(defn part-2
  [input]
  (balance-sleigh (map #(Integer/parseInt %) input) 4))