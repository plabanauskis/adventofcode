(ns aoc.y2017.day-1)

(defn part-1
  [input]
  (let [digits (map #(Integer/parseInt (str %)) input)
        digits (concat digits [(first digits)])]
    (->> digits
         (partition 2 1)
         (filter (fn [[a b]] (= a b)))
         (map first)
         (apply +))))

(defn part-2
  [input]
  (let [digits (map #(Integer/parseInt (str %)) input)
        len (count digits)
        digits (concat digits (take (/ len 2) digits))]
    (->> digits
         (partition (inc (/ len 2)) 1)
         (filter (fn [coll] (= (first coll) (last coll))))
         (map first)
         (apply +))))