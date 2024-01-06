(ns aoc.y2015.day-18)

(def ^:dynamic steps 100)

(defn- surrounding-indexes [x y]
  (for [i (range (dec x) (+ x 2))
        j (range (dec y) (+ y 2))
        :when (not (and (= i x) (= j y)))]
    [i j]))

(defn- get-light
  [matrix [row-ix col-ix]]
  (nth (nth matrix row-ix) col-ix))

(defn- count-neighbours
  [matrix [row-ix col-ix light]]
  (let [switched-on (->> (surrounding-indexes row-ix col-ix)
                         (filter (fn [[ri ci]]
                                   (and (not (neg? ri)) (not (neg? ci))
                                        (< ri (count matrix)) (< ci (count (first matrix))))))
                         (map (partial get-light matrix))
                         (filter #(= \# %))
                         count)]
    [row-ix col-ix light switched-on]))

(defn- indexed
  [matrix]
  (for [row-ix (range (count matrix))
        :let [row (nth matrix row-ix)]
        [col-ix light] (map-indexed vector row)]
    [row-ix col-ix light]))

(defn- change-state-pt-1
  [[row-ix col-ix light on-neighbours]]
  [row-ix
   col-ix
   (case light
     \# (if (or (= 2 on-neighbours) (= 3 on-neighbours)) \# \.)
     \. (if (= 3 on-neighbours) \# \.))])

(defn- solve
  [matrix change-state-fn]
  (->> (loop [matrix matrix
              step 0]
         (if (= steps step)
           matrix
           (recur (->> matrix
                       indexed
                       (map (partial count-neighbours matrix))
                       (map change-state-fn)
                       (partition-by first)
                       (map (fn [row] (map #(nth % 2) row))))
                  (inc step))))
       flatten
       (filter #(= \# %))
       count))

(defn part-1
  [input]
  (let [matrix (map seq input)]
    (solve matrix change-state-pt-1)))

(defn- change-state-pt-2
  [matrix [row-ix col-ix light on-neighbours]]
  [row-ix
   col-ix
   (if (contains? #{[0 0]
                    [0 (dec (count (first matrix)))]
                    [(dec (count matrix)) 0]
                    [(dec (count matrix)) (dec (count (first matrix)))]}
                  [row-ix col-ix])
     \#
     (case light
       \# (if (or (= 2 on-neighbours) (= 3 on-neighbours)) \# \.)
       \. (if (= 3 on-neighbours) \# \.)))])

(defn part-2
  [input]
  (let [matrix (map seq input)]
    (solve matrix (partial change-state-pt-2 matrix))))