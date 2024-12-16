(ns aoc.y2024.day-12
  (:require [clojure.set]))

(defn- parse-input
  [input]
  (->> input
       (map #(re-seq #"\w" %))
       (mapv vec)))

(defn- get-neighbors
  [[row col] kind]
  (->> [[row (dec col)]
        (when (= :all kind) [(dec row) (dec col)])
        [(dec row) col]
        (when (= :all kind) [(dec row) (inc col)])
        [row (inc col)]
        (when (= :all kind) [(inc row) (inc col)])
        [(inc row) col]
        (when (= :all kind) [(inc row) (dec col)])]
       (remove nil?)))

(defn- calculate-region-price
  [grid [row col]]
  (loop [to-visit #{[row col]}
         visited #{}
         fence 0
         area 0]
    (if (empty? to-visit)
      {:region-price (* fence area)
       :region-cells visited}
      (let [current (first to-visit)
            neighbors (get-neighbors current :adjacent)
            same-region-neighbors (filter #(= (get-in grid %) (get-in grid [row col])) neighbors)
            cell-fence (- 4 (count same-region-neighbors))
            new-to-visit (into (disj to-visit current)
                               (->> same-region-neighbors
                                    (filter (complement visited))))]
        (recur new-to-visit
               (conj visited current)
               (+ fence cell-fence)
               (inc area))))))

(defn- calculate-discounted-region-price
  [grid [row col]]
  (loop [to-visit #{[row col]}
         visited #{}
         corners 0
         area 0]
    (if (empty? to-visit)
      {:region-price (* corners area)
       :region-cells visited}
      (let [current (first to-visit)
            current-cell-type (get-in grid current)
            neighbors (get-neighbors current :all)
            corner-neighbors (partition 3 2 (concat neighbors (take 1 neighbors)))
            neighbor-types (map (fn [neighbors] (map #(get-in grid %) neighbors))
                                corner-neighbors)
            corner-count (->> neighbor-types
                              (filter (fn [neighbors]
                                        (or (and (not= (first neighbors) current-cell-type)
                                                 (not= (last neighbors) current-cell-type))
                                            (and (= (first neighbors) current-cell-type)
                                                 (= (last neighbors) current-cell-type)
                                                 (not= (second neighbors) current-cell-type)))))
                              count)
            same-region-neighbors (filter #(= (get-in grid %) (get-in grid [row col])) (get-neighbors current :adjacent))
            new-to-visit (into (disj to-visit current)
                               (->> same-region-neighbors
                                    (filter (complement visited))))]
        (recur new-to-visit
               (conj visited current)
               (+ corners corner-count)
               (inc area))))))

(defn- calculate-price
  [calculate-region-price-fn grid]
  (let [cell-positions (for [row (range (count grid))
                             col (range (count (first grid)))]
                         [row col])]
    (loop [visited-cells #{}
           cell 0
           price 0]
      (cond
        (= (count visited-cells) (count cell-positions)) price
        (visited-cells (nth cell-positions cell)) (recur visited-cells (inc cell) price)
        :else (let [{:keys [region-price region-cells]}
                    (calculate-region-price-fn grid (nth cell-positions cell))]
                (recur (clojure.set/union visited-cells region-cells)
                       (inc cell)
                       (+ price region-price)))))))

(defn part-1
  [input]
  (->> input
       parse-input
       (calculate-price calculate-region-price)))

(defn part-2
  [input]
  (->> input
       parse-input
       (calculate-price calculate-discounted-region-price)))
