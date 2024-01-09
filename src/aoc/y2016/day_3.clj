(ns aoc.y2016.day-3
  (:require [clojure.string]))

(defn- parse-line
  [l]
  (->> l
       (re-matches #".+?(\d+)\s+(\d+)\s+(\d+)")
       (drop 1)
       (map #(Integer/parseInt %))))

(defn- possible?
  [[a b c]]
  (and (> (+ a b) c)
       (> (+ b c) a)
       (> (+ a c) b)))

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (filter possible?)
       count))

(defn part-2
  [input]
  (->> input
       (map parse-line)
       (partition 3)
       (map #(apply map vector %))
       (mapcat identity)
       (filter possible?)
       count))