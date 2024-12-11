(ns aoc.y2024.day-11)

(defn- parse-input
  [input]
  (->> input
       (re-seq #"\d+")
       (map parse-long)))

(defn- apply-rule
  [[stone num]]
  (cond
    (zero? stone) [[1 num]]
    (->> stone str count even?) (let [s (str stone)
                                      l (count s)]
                                  [[(-> s (subs 0 (quot l 2)) parse-long) num]
                                   [(-> s (subs (quot l 2)) parse-long) num]])
    :else [[(* 2024 stone) num]]))

(defn- blink
  [stones]
  (let [new-stones (->> stones
                        (map apply-rule)
                        (apply concat))]
    (reduce (fn [updated-stones [stone num]]
              (update updated-stones stone (fnil + 0) num))
            {}
            new-stones)))

(defn part-1
  [input]
  (loop [i 0
         stones (frequencies (parse-input input))]
    (if (= i 25)
      (->> stones vals (apply +))
      (recur (inc i) (blink stones)))))

(defn part-2
  [input]
  (loop [i 0
         stones (frequencies (parse-input input))]
    (if (= i 75)
      (->> stones vals (apply +))
      (recur (inc i) (blink stones)))))
