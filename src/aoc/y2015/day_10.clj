(ns aoc.y2015.day-10)

(defn- look-and-say
  [ns]
  (->> ns
       (partition-by identity)
       (map #(vector (count %) (first %)))
       flatten))

(defn part-1
  [input]
  (->> input
       (map #(Integer/parseInt (str %)))
       (iterate look-and-say)
       (drop 40)
       first
       count))

(defn part-2
  [input]
  (->> input
       (map #(Integer/parseInt (str %)))
       (iterate look-and-say)
       (drop 50)
       first
       count))