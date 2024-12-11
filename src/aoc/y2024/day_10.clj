(ns aoc.y2024.day-10
  (:import (clojure.lang PersistentQueue)))

(defn- parse-line
  [l]
  (->> (re-seq #"\d" l)
       (mapv parse-long)))

(defn- get-height
  [grid [row col :as pos]]
  (when (and (<= 0 row (dec (count grid)))
             (<= 0 col (dec (count (first grid)))))
    (get-in grid pos)))

(defn- neighbors
  [[row col]]
  [[(dec row) col]
   [(inc row) col]
   [row (dec col)]
   [row (inc col)]])

(defn- find-paths
  [grid start]
  (loop [paths #{}
         queue (conj PersistentQueue/EMPTY [start #{start}])]
    (if (empty? queue)
      paths
      (let [[pos visited] (peek queue)
            curr-height (get-height grid pos)
            next-height (inc curr-height)
            next-positions (for [next-pos (neighbors pos)
                                 :let [h (get-height grid next-pos)]
                                 :when (and (= h next-height)
                                            (not (visited next-pos)))]
                             next-pos)]
        (if (= curr-height 9)
          (recur (conj paths visited)
                 (pop queue))
          (recur paths
                 (into (pop queue)
                       (for [next-pos next-positions]
                         [next-pos (conj visited next-pos)]))))))))

(defn- find-trailheads
  [grid]
  (for [rows (range (count grid))
        cols (range (count (first grid)))
        :when (zero? (get-in grid [rows cols]))]
    [rows cols]))

(defn- count-reachable-nines
  [grid start]
  (->> (find-paths grid start)
       (mapcat identity)
       (filter #(= 9 (get-height grid %)))
       distinct
       count))

(defn part-1
  [input]
  (let [grid (mapv parse-line input)]
    (->> grid
         find-trailheads
         (map #(count-reachable-nines grid %))
         (apply +))))

(defn- find-all-paths
  [grid start]
  (loop [paths #{}
         queue (conj PersistentQueue/EMPTY [start [start]])]
    (if (empty? queue)
      paths
      (let [[pos path] (peek queue)
            curr-height (get-height grid pos)
            next-height (inc curr-height)
            next-positions (for [next-pos (neighbors pos)
                                 :let [h (get-height grid next-pos)]
                                 :when (= h next-height)]
                             next-pos)]
        (if (= curr-height 9)
          (recur (conj paths path)
                 (pop queue))
          (recur paths
                 (into (pop queue)
                       (for [next-pos next-positions]
                         [next-pos (conj path next-pos)]))))))))

(defn- count-distinct-paths
  [grid start]
  (count (find-all-paths grid start)))

(defn part-2
  [input]
  (let [grid (mapv parse-line input)]
    (->> grid
         find-trailheads
         (map #(count-distinct-paths grid %))
         (apply +))))
