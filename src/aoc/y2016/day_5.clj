(ns aoc.y2016.day-5
  (:require [utils :refer [md5-hash]]))

(defn part-1
  [input]
  (->> (iterate inc 0)
       (map #(str input %))
       (pmap md5-hash)
       (filter #(= "00000" (subs % 0 5)))
       (map #(nth % 5))
       (take 8)
       (apply str)))

(defn part-2
  [input]
  (->> (iterate inc 0)
       (map #(str input %))
       (pmap md5-hash)
       (filter #(= "00000" (subs % 0 5)))
       (filter #(< (int (nth % 5)) (int \8)))
       (reduce (fn [pwd h]
                 (if (some nil? pwd)
                   (let [ix (- (int (nth h 5)) (int \0))
                         v (nth h 6)]
                     (if (nth pwd ix)
                       pwd
                       (assoc pwd ix v)))
                   (reduced pwd)))
               (vec (repeat 8 nil)))
       (apply str)))