(ns aoc.y2016.day-16)

(def ^:dynamic disk 272)

(defn- generate-data
  [len initial-data]
  (loop [a initial-data
         l (count initial-data)]
    (if (<= len l)
      (take len a)
      (recur (concat a [\0] (->> a
                                 reverse
                                 (map #(if (= \1 %) \0 \1))))
             (inc (* 2 l))))))

(defn- checksum
  [data]
  (loop [chk data
         l (count data)]
    (if-not (zero? (mod l 2))
      chk
      (recur (->> chk
                  (partition 2)
                  (map (fn [[a b]] (if (= a b) \1 \0))))
             (/ l 2)))))

(defn part-1
  [input]
  (->> input
       (generate-data disk)
       checksum
       (apply str)))

(defn part-2
  [input]
  (->> input
       (generate-data 35651584)
       doall
       checksum
       (apply str)))