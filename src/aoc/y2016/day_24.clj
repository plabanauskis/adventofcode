(ns aoc.y2016.day-24
  (:require [clojure.math.combinatorics :as comb]))

(defn- row-count
  [m]
  (count m))

(defn- col-count
  [m]
  (count (first m)))

(defn- find-locations
  [m]
  (into {}
        (for [line (range (count m))
              pos (range (count (first m)))
              :let [location (-> m (nth line) (nth pos))]
              :when (<= (int \0)
                        (int location)
                        (int \9))]
          [location [line pos]])))

(defn- valid-neighbor?
  [m [row pos]]
  (let [max-row (row-count m)
        max-col (col-count m)]
    (and (< -1 row max-row)
         (< -1 pos max-col)
         (not= (get-in m [row pos]) \#))))

(defn- neighbors
  [[row pos]]
  [[(dec row) pos] [row (dec pos)] [(inc row) pos] [row (inc pos)]])

(defn- find-distance
  [m s t locations]
  (let [start (get locations s)
        target (get locations t)]
    (loop [to-visit [[start 0]]
           visited #{start}]
      (when (not-empty to-visit)
        (let [[current distance] (first to-visit)
              to-visit (rest to-visit)]
          (if (= current target)
            distance
            (let [adjacent (filter #(valid-neighbor? m %) (neighbors current))
                  new-visits (remove visited adjacent)
                  new-to-visit (concat to-visit (map #(conj [%] (inc distance)) new-visits))
                  new-visited (apply conj visited new-visits)]
              (recur new-to-visit new-visited))))))))

(defn- calculate-distances
  [m locations]
  (let [num-locations (count locations)
        distances (vec (repeat num-locations
                               (vec (repeat num-locations 0))))
        pairs (for [i (range 0 (dec num-locations))
                    j (range (inc i) num-locations)
                    :let [distance (find-distance m (char (+ i 48)) (char (+ j 48)) locations)]]
                [i j distance])]
    (reduce (fn [matrix [row col distance]]
              (-> matrix
                  (assoc-in [row col] distance)
                  (assoc-in [col row] distance)))
            distances
            pairs)))

(defn- find-path-distance
  [distances path]
  (->> path
       (partition 2 1)
       (map #(get-in distances %))
       (apply +)))

(defn part-1
  [input]
  (let [input (vec input)
        locations (find-locations input)
        distances (calculate-distances input locations)]
    (->> distances
         count
         (range 1)
         comb/permutations
         (map (partial cons 0))
         (map (partial find-path-distance distances))
         (apply min))))

(defn part-2
  [input]
  (let [input (vec input)
        locations (find-locations input)
        distances (calculate-distances input locations)]
    (->> distances
         count
         (range 1)
         comb/permutations
         (map (partial into [0]))
         (map #(conj % 0))
         (map (partial find-path-distance distances))
         (apply min))))