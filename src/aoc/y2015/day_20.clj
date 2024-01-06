(ns aoc.y2015.day-20
  (:require [clojure.math :as math]))

(defn- divisors
  [n]
  (->> (range 1 (inc (math/sqrt n)))
       (filter #(zero? (mod n %)))
       (mapcat #(vector % (/ n %)))
       set))

(defn- house-presents-pt1
  [h]
  (->> (divisors h)
       (map #(* 10 %))
       (apply +)))

(defn part-1
  [input]
  (let [n (Integer/parseInt input)]
    (->> (iterate inc 1)
         (drop-while #(< (house-presents-pt1 %) n))
         first)))

(defn- house-presents-pt2
  [h]
  (->> (divisors h)
       (filter #(>= (* 50 %) h))
       (map #(* 11 %))
       (apply +)))

(defn part-2
  [input]
  (let [n (Integer/parseInt input)]
    (->> (iterate inc 1)
         (drop-while #(< (house-presents-pt2 %) n))
         first)))