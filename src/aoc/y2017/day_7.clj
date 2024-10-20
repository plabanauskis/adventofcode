(ns aoc.y2017.day-7
  (:require [clojure.set]))

(defn- parse-line
  [l]
  (let [[_ name weight children-str] (re-matches #"(\w+) \((\d+)\)(?: -> (.*))?" l)]
    {:name name
     :weight (Integer/parseInt weight)
     :children (when children-str (clojure.string/split children-str #", "))}))

(defn- parse-input
  [input]
  (->> input
       (map parse-line)
       (reduce (fn [acc item]
                 (let [name (:name item)
                       rest-props (dissoc item :name)]
                   (assoc acc name rest-props)))
               {})))

(defn- find-root
  [programs]
  (let [program-names (->> programs keys set)
        child-names (->> programs
                         vals
                         (map :children)
                         (apply concat)
                         set)]
    (first (clojure.set/difference program-names child-names))))

(defn part-1
  [input]
  (let [programs (parse-input input)]
    (find-root programs)))

(defn- find-weights
  [p programs]
  (let [children (get-in programs [p :children])
        weight (get-in programs [p :weight])
        child-weights (map #(find-weights % programs) children)]
    [p
     (when children child-weights)
     (+ (reduce + (map last child-weights))
        weight)]))

(defn- find-outlier
  [weights]
  (let [by-acc-weight (group-by last weights)]
    (some (fn [[_ v]] (when (= 1 (count v)) (first v))) by-acc-weight)))

(defn part-2
  [input]
  (let [programs (parse-input input)
        root (find-root programs)
        weights (find-weights root programs)]
    (loop [q [(second weights)]]
      (let [outlier (find-outlier (last q))]
        (if outlier
          (recur (conj q (second outlier)))
          (let [weights (last (drop-last q))
                outlier (find-outlier weights)
                [outlier-name _ outlier-weight] outlier
                [_ _ correct-weight] (first (filter #(not= outlier-name (first %)) weights))
                diff (- correct-weight outlier-weight)]
            (+ (get-in programs [outlier-name :weight]) diff)))))))