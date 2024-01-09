(ns aoc.y2016.day-6)

(defn- solve
  [input sort-fn]
  (->> input
       (apply (partial map vector))
       (map frequencies)
       (map #(sort-by second sort-fn %))
       (map ffirst)
       (apply str)))

(defn part-1
  [input]
  (solve input >))

(defn part-2
  [input]
  (solve input <))