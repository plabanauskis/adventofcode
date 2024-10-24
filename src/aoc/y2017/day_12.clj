(ns aoc.y2017.day-12
  (:require [clojure.set]))

(defn- parse-line
  [l]
  (let [[_ nums] (re-matches #".+ <-> (.+)" l)]
    (map #(Integer/parseInt %)
         (clojure.string/split nums #", "))))

(defn- get-reachable-from
  [all-recipients start]
  (loop [reachable #{start}
         to-visit [start]]
    (let [recipients (filter (complement reachable)
                             (set (mapcat #(nth all-recipients %) to-visit)))]
      (if (empty? recipients)
        reachable
        (recur (into reachable recipients) recipients)))))

(defn part-1
  [input]
  (let [all-recipients (mapv parse-line input)]
    (count (get-reachable-from all-recipients 0))))

(defn part-2
  [input]
  (let [all-recipients (mapv parse-line input)
        all-programs (set (range (count all-recipients)))]
    (loop [remaining-programs all-programs
           reachable-groups []]
      (if (empty? remaining-programs)
        (count reachable-groups)
        (let [start (apply min remaining-programs)
              reachable-from-start (get-reachable-from all-recipients start)
              new-remaining-programs (clojure.set/difference remaining-programs reachable-from-start)]
          (recur new-remaining-programs
                 (conj reachable-groups reachable-from-start)))))))
