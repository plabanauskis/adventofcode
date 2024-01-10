(ns aoc.y2016.day-18)

(def ^:dynamic rows 40)

(defn- next-row
  [r]
  (->> (concat [\.] r [\.])
       (partition 3 1)
       (map (fn [tiles]
              (case tiles
                ([\^ \^ \.] [\. \^ \^] [\^ \. \.] [\. \. \^]) \^
                \.)))))

(defn- solve
  [row n]
  (loop [row row
         safe (count (filter #(= \. %) row))
         idx 0]
    (if (= idx (dec n))
      safe
      (let [nxt-row (next-row row)]
        (recur nxt-row
               (+ safe (count (filter #(= \. %) nxt-row)))
               (inc idx))))))

(defn part-1
  [input]
  (solve input rows))

(defn part-2
  [input]
  (solve input 400000))