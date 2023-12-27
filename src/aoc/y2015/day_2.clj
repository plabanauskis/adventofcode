(ns aoc.y2015.day-2
  (:require [clojure.string :as str]))

(defn- parse-line
  [l]
  (map #(Integer/parseInt %) (str/split l #"x")))

(defn- calc-paper-length
  [[l w h]]
  (let [a (* l w)
        b (* w h)
        c (* h l)]
    (+ (* 2 a) (* 2 b) (* 2 c) (min a b c))))

(defn part-1
  [input]
  (->>
    input
    (map parse-line)
    (map calc-paper-length)
    (apply +)))

(defn- calc-ribbon-length
  [dims]
  (+ (->> dims sort butlast (apply +) (* 2))
     (apply * dims)))

(defn part-2
  [input]
  (->>
    input
    (map parse-line)
    (map calc-ribbon-length)
    (apply +)))