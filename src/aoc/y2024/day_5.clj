(ns aoc.y2024.day-5)

(defn- parse-ordering-rule
  [line]
  (->> line
       (re-seq #"\d+")
       (mapv parse-long)))

(defn- parse-update
  [line]
  (->> (clojure.string/split line #",")
       (mapv parse-long)))

(defn- parse-input
  [input]
  (let [ordering-rules (take-while #(not= % "") input)
        updates (drop (inc (count ordering-rules)) input)]
    [(set (map parse-ordering-rule ordering-rules))
     (map parse-update updates)]))

(defn- right-order?
  [update ordering-rules]
  (->> update
       (partition 2 1)
       (map vec)
       (every? ordering-rules)))

(defn part-1
  [input]
  (let [[ordering-rules updates] (parse-input input)]
    (->> updates
         (filter #(right-order? % ordering-rules))
         (map #(nth % (/ (count %) 2)))
         (apply +))))

(defn- insert
  [pages page ordering-rules]
  (let [insert-after (->> ordering-rules
                          (filter (fn [[_ b]] (= b page)))
                          (map first))]
    (if (empty? insert-after)
      (cons page pages)
      (let [i (apply max (map #(.indexOf pages %) insert-after))]
        (vec (concat (take (inc i) pages)
                     [page]
                     (drop (inc i) pages)))))))

(defn- fix-update
  [update ordering-rules]
  (let [correct-pairs (->> update
                           (partition 2 1)
                           (mapcat (fn [[a b]] [[a b] [b a]]))
                           (filter ordering-rules))]
    (reduce (fn [pages [a b]]
              (let [page-set (set pages)]
                (cond
                  (page-set a) (insert pages b ordering-rules)
                  (page-set b) (insert pages a ordering-rules)
                  :else pages)))
            (first correct-pairs)
            (drop 1 correct-pairs))))

(defn part-2
  [input]
  (let [[ordering-rules updates] (parse-input input)]
    (->> updates
         (remove #(right-order? % ordering-rules))
         (map #(fix-update % ordering-rules))
         (map #(nth % (/ (count %) 2)))
         (apply +))))
