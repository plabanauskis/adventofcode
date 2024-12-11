(ns aoc.y2024.day-3)

(defn part-1
  [input]
  (->> input
       (mapcat #(re-seq #"mul\((\d+),(\d+)\)" %))
       (map (fn [[_ a b]] (* (Integer/parseInt a) (Integer/parseInt b))))
       (apply +)))

(defn part-2
  [input]
  (->> input
       (mapcat #(re-seq #"mul\((\d+),(\d+)\)|(do\(\))|(don't\(\))" %))
       (reduce (fn [[do? ins] op]
                 (case (first op)
                   "do()" [true ins]
                   "don't()" [false ins]
                   [do? (if do? (conj ins op) ins)]))
               [true []])
       second
       (map (fn [[_ a b]] (* (Integer/parseInt a) (Integer/parseInt b))))
       (apply +)))