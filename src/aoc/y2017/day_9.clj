(ns aoc.y2017.day-9)

(defn part-1
  [input]
  (->> input
       (reduce
         (fn [[score current cancel? garbage?] c]
           (cond
             cancel? [score current false garbage?]
             (= \! c) [score current true garbage?]
             (= \{ c) [score (if garbage? current (inc current)) false garbage?]
             (= \} c) [(if garbage? score (+ current score)) (if garbage? current (dec current)) false garbage?]
             (= \< c) [score current false true]
             (= \> c) [score current false false]
             :else [score current false garbage?]))
         [0 0 false false])
       first))

(defn part-2
  [input]
  (->> input
       (reduce
         (fn [[n cancel? garbage?] c]
           (cond
             cancel? [n false garbage?]
             (= \! c) [n true garbage?]
             (and garbage? (not= \> c)) [(inc n) false true]
             (= \< c) [n false true]
             (= \> c) [n false false]
             :else [(+ n (if garbage? 1 0)) false garbage?]))
         [0 false false])
       first))
