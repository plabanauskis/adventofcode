(ns aoc.y2015.day-17
  (:require [clojure.math.combinatorics :as comb]))

(def ^:dynamic volume 150)

(defn part-1
  [input]
  (->> input
       (map #(Integer/parseInt %))
       (map-indexed vector)
       comb/subsets
       (map #(map second %))
       (map #(apply + %))
       (filter #(= volume %))
       count))

(defn part-2
  [input]
  (->> input
       (map #(Integer/parseInt %))
       (map-indexed vector)
       comb/subsets
       (map #(map second %))
       (map (fn [cs] [(count cs) (apply + cs)]))
       (filter #(= volume (second %)))
       (sort-by first)
       (partition-by first)
       first
       count))