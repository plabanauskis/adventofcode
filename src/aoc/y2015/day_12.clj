(ns aoc.y2015.day-12
  (:require [cheshire.core :as json]))

(defn- deep-flatten
  [x]
  (cond
    (coll? x) (mapcat deep-flatten (if (map? x) (apply concat x) x))
    :else [x]))

(defn part-1
  [input]
  (->> input
       json/parse-string
       deep-flatten
       (filter int?)
       (apply +)))

(defn- contains-red? [m]
  (some #(= "red" %) (vals m)))

(defn- deep-flatten-no-red-obj [x]
  (cond
    (map? x) (if (contains-red? x) [] (mapcat deep-flatten-no-red-obj (apply concat x)))
    (coll? x) (mapcat deep-flatten-no-red-obj x)
    :else [x]))

(defn part-2
  [input]
  (->> input
       json/parse-string
       deep-flatten-no-red-obj
       (filter int?)
       (apply +)))