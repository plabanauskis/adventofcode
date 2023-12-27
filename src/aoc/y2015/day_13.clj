(ns aoc.y2015.day-13
  (:require [clojure.math.combinatorics :as comb]))

(defn- parse-line
  [l]
  (let [[_ p1 action units p2] (re-matches #"^(\w+?) .+(gain|lose) (\d+?) .+ (\w+)\.$" l)]
    [p1 p2 (case action
             "gain" (Integer/parseInt units)
             "lose" (- (Integer/parseInt units)))]))

(defn- build-map
  [acc [from to value]]
  (update acc from #(assoc % to value)))

(defn- total
  [positions persons]
  (->> persons
       (partition 2 1)
       (reduce (fn [acc [from to]]
                 (+ acc ((positions from) to) ((positions to) from)))
               0)))

(defn- solve
  [positions]
  (let [init-person (first (keys positions))
        rest-persons (comb/permutations (rest (keys positions)))]
    (->> rest-persons
         (map #(concat [init-person] % [init-person]))
         (map (partial total positions))
         (apply max))))

(defn part-1
  [input]
  (let [positions (->> input
                       (map parse-line)
                       (reduce build-map {}))]
    (solve positions)))

(defn- add-me
  [positions]
  (letfn [(plus-me [name]
            [["Me" name 0]
             [name "Me" 0]])]
    (mapcat plus-me (keys positions))))

(defn part-2
  [input]
  (let [positions (->> input
                       (map parse-line)
                       (reduce build-map {}))
        positions (reduce build-map positions (add-me positions))]
    (solve positions)))