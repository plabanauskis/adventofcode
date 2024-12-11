(ns aoc.y2024.day-8
  (:require [clojure.math.combinatorics :as comb]))

(defn- parse-input
  [input]
  (let [input (vec input)
        rows (count input)
        cols (count (first input))
        antennas (for [row (range rows)
                       col (range cols)
                       :let [frequency (get-in input [row col])]
                       :when (not= frequency \.)]
                   {:pos [row col]
                    :frequency frequency})]
    {:area [rows cols]
     :antennas (update-vals (group-by :frequency antennas)
                            #(mapv :pos %))}))

(defn- inside-area?
  [[rows cols] [row col]]
  (and (<= 0 row (dec rows))
       (<= 0 col (dec cols))))

(defn- find-antinodes
  [antennas]
  (->> (comb/combinations antennas 2)
       (mapcat (fn [[a b]]
                 (let [diff (mapv - a b)]
                   [(mapv + a diff)
                    (mapv - b diff)])))))

(defn part-1
  [input]
  (let [{:keys [area antennas]} (parse-input input)]
    (->> (vals antennas)
         (mapcat find-antinodes)
         distinct
         (filter (partial inside-area? area))
         count)))

(defn- find-antinodes-with-harmonics
  [area antennas]
  (->> (comb/combinations antennas 2)
       (mapcat (fn [[a b]]
                 (let [diff (mapv - a b)
                       antinodes-inc (->> a
                                          (iterate #(mapv + % diff))
                                          (take-while (partial inside-area? area)))
                       antinodes-dec (->> a
                                          (iterate #(mapv - % diff))
                                          (take-while (partial inside-area? area)))]
                   (concat antinodes-inc antinodes-dec))))))

(defn part-2
  [input]
  (let [{:keys [area antennas]} (parse-input input)]
    (->> (vals antennas)
         (mapcat (partial find-antinodes-with-harmonics area))
         distinct
         count)))
