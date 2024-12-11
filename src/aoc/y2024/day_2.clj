(ns aoc.y2024.day-2)

(defn- parse-line
  [l]
  (->> l
       (re-seq #"\d+")
       (mapv #(Integer/parseInt %))))

(defn- safe?
  [report]
  (and (or (apply < report)
           (apply > report))
       (every? (fn [[a b]]
                 (and (<= 1 (abs (- a b)) 3)))
               (partition 2 1 report))))

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (filter safe?)
       count))

(defn part-2
  [input]
  (->> input
       (map parse-line)
       (filter #(or (safe? %)
                    (let [dampened (for [i (range (count %))]
                                     (concat (take i %) (drop (inc i) %)))]
                      (some safe? dampened))))
       count))