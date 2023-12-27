(ns aoc.y2015.day-9
  (:require [clojure.math.combinatorics :as comb]))

(defn- parse-line
  [l]
  (let [[_ a b d] (re-matches #"(\w+?) to (\w+?) = (\d+)" l)
        d (Integer/parseInt d)]
    {a [b d]
     b [a d]}))

(defn- apply-routes
  [routes [start end]]
  (if (routes start)
    (update-in routes [start] assoc (first end) (second end))
    (assoc routes start {(first end) (second end)})))

(defn- distance
  [routes path]
  (:distance (reduce (fn [acc e]
                       {:current e
                        :distance (+ (:distance acc) ((routes (:current acc)) e))})
                     {:current (first path) :distance 0}
                     (drop 1 path))))

(defn- solve
  [input f]
  (let [routes (->> input
                    (map parse-line)
                    (mapcat seq)
                    (reduce apply-routes {}))]
    (->> (keys routes)
         comb/permutations
         (map (partial distance routes))
         (apply f))))

(defn part-1
  [input]
  (solve input min))

(defn part-2
  [input]
  (solve input max))