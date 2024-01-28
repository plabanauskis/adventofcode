(ns aoc.y2016.day-22
  (:require [clojure.string :as string]
            [clojure.math.combinatorics :as comb]))

(defn- parse-line
  [l]
  (let [parse-suffixed #(subs % 0 (dec (count %)))
        [fs size used avail use] (string/split l #"\s+")
        [_ x y] (re-find #"x(\d+)-y(\d+)" fs)
        x (Integer/parseInt x)
        y (Integer/parseInt y)
        [size used avail use] (map (comp #(Integer/parseInt %) parse-suffixed) [size used avail use])
        syms '[fs x y size used avail use]]
    (zipmap (map (comp keyword name) syms)
            [fs x y size used avail use])))

(defn- viable-pair?
  [[a b]]
  (and (pos? (:use a))
       (<= (:used a) (:avail b))))

(defn part-1
  [input]
  (let [disks (map parse-line (drop 2 input))]
    (->> (comb/permuted-combinations disks 2)
         (filter viable-pair?)
         count)))

(defn part-2
  [_]
  (+ 9                                                      ; move empty data up
     34                                                     ; move empty data left
     2                                                      ; move empty data up
     33                                                     ; move empty data right
     16                                                     ; move empty data up
     (* 5 34)                                               ; move goal data left
     1))                                                    ; last move of goal data