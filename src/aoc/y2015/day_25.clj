(ns aoc.y2015.day-25)

(defn- parse-line
  [l]
  (->> l
       (re-matches #".+row (\d+), column (\d+).+")
       (drop 1)
       (map #(Integer/parseInt %))))

(defn- value-count [row col]
  (let [diagonal (+ row col -2)]
    (+ (/ (* diagonal (inc diagonal)) 2) col)))

(defn part-1
  [input]
  (let [[row col] (parse-line input)]
    (->> 20151125
         (iterate #(rem (* % 252533) 33554393))
         (drop (dec (value-count row col)))
         first)))

(defn part-2
  [_]
  "The end!")