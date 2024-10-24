(ns aoc.y2017.day-11
  (:require [clojure.string]))

(defn- step
  [[x y z] dir]
  (case dir
    "n" [(inc x) (dec y) z]
    "s" [(dec x) (inc y) z]
    "ne" [(inc x) y (dec z)]
    "sw" [(dec x) y (inc z)]
    "se" [x (inc y) (dec z)]
    "nw" [x (dec y) (inc z)]))

(defn part-1
  [input]
  (->> (clojure.string/split input #",")
       (reduce step [0 0 0])
       (map abs)
       (apply #(/ (+ %1 %2 %3) 2))))

(defn part-2
  [input]
  (let [path (->> (clojure.string/split input #",")
                  (reduce (fn [[x y z max-distance] dir]
                            (let [[nx ny nz] (step [x y z] dir)
                                  current-distance (/ (+ (abs nx) (abs ny) (abs nz)) 2)]
                              [nx ny nz (max max-distance current-distance)]))
                          [0 0 0 0]))]
    (nth path 3)))
