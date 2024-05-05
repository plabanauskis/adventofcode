(ns aoc.y2017.day-2
  (:require [clojure.math.combinatorics :as comb]))

(defn- parse-line
  [l]
  (->> (clojure.string/split l #"\t")
       (map #(Integer/parseInt %))))

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (map #(vector (apply max %) (apply min %)))
       (map #(apply - %))
       (apply +)))

(defn- get-division
  [coll]
  (->> (comb/combinations coll 2)
       (filter (fn [[a b]] (or (zero? (mod a b))
                               (zero? (mod b a)))))
       first
       ((fn [[a b]]
          (if (zero? (mod a b))
            (/ a b)
            (/ b a))))))

(defn part-2
  [input]
  (->> input
       (map parse-line)
       (map get-division)
       (apply +)))