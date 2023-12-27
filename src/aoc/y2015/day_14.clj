(ns aoc.y2015.day-14)

(def ^:dynamic travel-time 2503)

(defn- parse-line
  [l]
  (let [[_ speed fly-time rest-time] (re-matches #".+ (\d+?) .+ (\d+?) .+ (\d+?) .+" l)]
    [(Integer/parseInt speed) (Integer/parseInt fly-time) (Integer/parseInt rest-time)]))

(defn- traveled-distance
  [[speed fly-time rest-time]]
  (let [full-cycles (int (/ travel-time (+ fly-time rest-time)))
        last-cycle (mod travel-time (* full-cycles (+ fly-time rest-time)))
        extra-fly-distance (if (>= last-cycle fly-time) (* fly-time speed) (* last-cycle speed))]
    (+ (* full-cycles fly-time speed) extra-fly-distance)))

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (map traveled-distance)
       (apply max)))

(defn- accumulate-traveled-distance
  [[speed fly-time rest-time]]
  (->> (cycle (concat (repeat fly-time speed) (repeat rest-time 0)))
       (take travel-time)
       (reductions +)))

(defn- max-index
  [coll]
  (->> coll
       (map-indexed vector)
       (apply (partial max-key second))
       first))

(defn- increase-score
  [scores values]
  (let [placeholder (vec (repeat (count values) 0))
        winner (assoc placeholder (max-index values) 1)]
    (map + scores winner)))

(defn part-2
  [input]
  (->> input
       (map parse-line)
       (map accumulate-traveled-distance)
       (apply interleave)
       (partition (count input))
       (reduce increase-score (repeat (count input) 0))
       (apply max)))