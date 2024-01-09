(ns aoc.y2016.day-1)

(defn- parse-input
  [input]
  (-> input
      (clojure.string/split #", ")
      (as-> m (map #(vector (first %) (subs % 1)) m))
      (as-> m (map (fn [[d n]] [(keyword (str d)) (Integer/parseInt (str n))]) m))))

(def ^:private directions
  {[0 1] {:L [-1 0] :R [1 0]}
   [1 0] {:L [0 1] :R [0 -1]}
   [0 -1] {:L [1 0] :R [-1 0]}
   [-1 0] {:L [0 -1] :R [0 1]}})

(defn- move
  [[[x y] dir] [d n]]
  (let [[dx dy] (-> directions
                    (get dir)
                    (get d))]
    [[(+ x (* n dx))
      (+ y (* n dy))]
     [dx dy]]))

(defn part-1
  [input]
  (->> input
       parse-input
       (reduce move [[0 0] [0 1] #{}])
       first
       (map #(Math/abs ^int %))
       (apply +)))

(defn- move-until-visited-twice
  [[[x y] dir visited] [d n]]
  (let [[dx dy] (-> directions
                    (get dir)
                    (get d))
        [nx ny] [(+ x (* n dx))
                 (+ y (* n dy))]
        visited-positions (for [vx (range (min x nx) (inc (max x nx)))
                                vy (range (min y ny) (inc (max y ny)))
                                :when (not= [x y] [vx vy])]
                            [vx vy])]
    (if-let [bunny-hq (some visited visited-positions)]
      (reduced bunny-hq)
      [[nx ny] [dx dy] (apply conj visited visited-positions)])))

(defn part-2
  [input]
  (->> input
       parse-input
       (reduce move-until-visited-twice [[0 0] [0 1] #{[0 0]}])
       (map #(Math/abs ^int %))
       (apply +)))