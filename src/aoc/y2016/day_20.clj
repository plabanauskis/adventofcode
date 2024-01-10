(ns aoc.y2016.day-20)

(def ^:dynamic max-ip 4294967295)

(defn- parse-line
  [l]
  (let [[_ start end] (re-matches #"(\d+)-(\d+)" l)]
    [(Long/parseLong start) (Long/parseLong end)]))

(defn- merge-ranges-until-gap
  [[mstart mend :as acc] [start end]]
  (if (empty? acc)
    (if (> start 0)
      (reduced 0)
      [start end])
    (if (> (dec start) mend)
      (reduced (inc mend))
      [mstart (max mend end)])))

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (sort-by first)
       (reduce merge-ranges-until-gap [])))

(defn- merge-ranges
  [acc [start end]]
  (if (empty? acc)
    [[start end]]
    (let [[mstart mend :as l] (last acc)]
      (if (> (dec start) mend)
        (conj acc [start end])
        (assoc acc (dec (count acc)) [mstart (max mend end)])))))

(defn part-2
  [input]
  (->> input
       (map parse-line)
       (sort-by first)
       (reduce merge-ranges [])
       (map (fn [[start end]] (inc (- end start))))
       (apply +)
       (- (inc max-ip))))