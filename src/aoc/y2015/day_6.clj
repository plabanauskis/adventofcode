(ns aoc.y2015.day-6)

(defn- matrix
  [size v]
  (->> v (repeat size) (repeat size)))

(defn- replace-matrix-values
  [replacement-fn [ax ay bx by] m]
  (map-indexed (fn [i r]
                 (doall (map-indexed (fn [j c]
                                       (if (and (<= ay i by)
                                                (<= ax j bx))
                                         (replacement-fn c)
                                         c))
                                     r)))
               m))

(defn- interpret-instruction-part-1
  [inst]
  (case inst
    "turn on" (constantly true)
    "turn off" (constantly false)
    "toggle" #(not %)))

(defn- parse-line
  [instr-interpreter l]
  (let [el (->> l
                (re-matches #"(.+?) (\d+?),(\d+?) through (\d+?),(\d+?)")
                (drop 1))
        switch (instr-interpreter (first el))
        pos (->> (drop 1 el)
                 (map #(Integer/parseInt %)))]
    (partial replace-matrix-values switch pos)))

(defn part-1
  [input]
  (let [lights (matrix 1000 false)]
    (->> input
         (map (partial parse-line interpret-instruction-part-1))
         (reduce (fn [m reductor] (reductor m)) lights)
         flatten
         (filter true?)
         count)))

(defn- interpret-instruction-part-2
  [inst]
  (case inst
    "turn on" inc
    "turn off" #(if (> % 0) (dec %) %)
    "toggle" #(+ 2 %)))

(defn part-2
  [input]
  (let [lights (matrix 1000 0)]
    (->> input
         (map (partial parse-line interpret-instruction-part-2))
         (reduce (fn [m reductor] (reductor m)) lights)
         flatten
         (apply +))))