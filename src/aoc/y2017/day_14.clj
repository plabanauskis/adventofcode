(ns aoc.y2017.day-14
  (:require [clojure.string]
            [aoc.y2017.day-10 :refer [knot-hash]]
            [clojure.pprint]))

(def bitlength 128)

(defn- format-bytes
  [bytes]
  (->> bytes
       (map #(Integer/toBinaryString %))
       (map #(format "%8s" %))
       (map #(clojure.string/replace % " " "0"))))

(defn part-1
  [input]
  (->> (range bitlength)
       (map #(str input "-" %))
       (map #(knot-hash % format-bytes))
       (map (fn [hash]
              (->> hash
                   (filter #(= \1 %))
                   count)))
       (apply +)))

(defn- traverse
  [regions row col]
  (letfn [(find-unvisited-neighbours
            [regions row col visited]
            (let [neighbours (for [[drow dcol] [[-1 0] [1 0] [0 -1] [0 1]]
                                  :let [nrow (+ row drow)
                                        ncol (+ col dcol)]
                                  :when (and (>= nrow 0)
                                             (< nrow bitlength)
                                             (>= ncol 0)
                                             (< ncol bitlength)
                                             (not (visited [nrow ncol]))
                                             (= (get-in regions [nrow ncol]) \1))]
                            [nrow ncol])]
              neighbours))
          (traverse-impl
            [regions row col visited]
            (let [unvisited-neighbours (find-unvisited-neighbours regions row col visited)]
              (-> visited
                  (conj [row col])
                  (into (reduce (fn [visited [row col]]
                                  (traverse-impl regions row col visited))
                                visited
                                unvisited-neighbours)))))]
    (traverse-impl regions row col #{})))

(defn- count-regions
  [regions]
  (let [rowcol (vec (for [row (range bitlength)
                          col (range bitlength)]
                      [row col]))]
    (loop [visited #{}
           num-regions 0
           i 0]
      (if (= i (count rowcol))
        num-regions
        (let [[row col :as rc] (nth rowcol i)
              v (get-in regions rc)]
          (if (visited rc)
            (recur visited num-regions (inc i))
            (if (= v \0)
              (recur (conj visited rc) num-regions (inc i))
              (let [new-visited (traverse regions row col)]
                (recur (into visited new-visited) (inc num-regions) (inc i))))))))))

(defn part-2
  [input]
  (let [regions (vec (->> (range bitlength)
                          (map #(str input "-" %))
                          (mapv #(knot-hash % format-bytes))))]
    (count-regions regions)))
