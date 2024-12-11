(ns aoc.y2024.day-1)

(defn- parse-line
  [l]
  (let [[_ left right] (re-matches #"(\d+)\s+(\d+)" l)]
    [(Integer/parseInt left) (Integer/parseInt right)]))

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (apply map list)
       (map sort)
       (apply map -)
       (map abs)
       (apply +)))

(defn part-2
  [input]
  (let [[left right] (->> input
                          (map parse-line)
                          (apply map list))
        right (frequencies right)]
    (->> left
         (map #(* % (get right % 0)))
         (apply +))))