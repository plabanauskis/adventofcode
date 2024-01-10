(ns aoc.y2016.day-15)

(defn- parse-line
  [l]
  (->> (re-matches #".+?(\d+) positions.+position (\d+)." l)
       (drop 1)
       reverse
       (map #(Integer/parseInt %))
       vec))

(defn- spin-disc
  [times [curr all]]
  (let [new-pos (mod (+ curr times) all)]
    [new-pos all]))

(defn- solve
  [discs]
  (loop [time 0
         discs (map-indexed (fn [idx disc] (spin-disc idx disc)) discs)]
    (if (every? #(= 0 (first %)) discs)
      (dec time)
      (recur (inc time)
             (doall (map (partial spin-disc 1) discs))))))

(defn part-1
  [input]
  (solve (map parse-line input)))

(defn part-2
  [input]
  (solve (concat (map parse-line input) [[0 11]])))