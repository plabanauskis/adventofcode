(ns aoc.y2016.day-8
  (:require [clojure.string]))

(def ^:dynamic screen-size [50 6])

(defn- screen
  [[w h]]
  (->> false
       (repeat w)
       (repeat h)))

(defn- rotate [x coll]
  (let [len (count coll)
        nx (mod x len)]
    (->> (concat coll coll)
         (drop (- len nx))
         (take len))))

(defn- rect
  [cols rows scr]
  (for [r (range (count scr))]
    (if (< r rows)
      (concat (repeat cols true) (drop cols (nth scr r)))
      (nth scr r))))

(defn- rotate-row
  [row x scr]
  (concat (take row scr)
          (list (rotate x (nth scr row)))
          (drop (inc row) scr)))

(defn- rotate-col
  [col x scr]
  (let [ncol (->> scr
                  (map #(nth % col))
                  (rotate x))]
    (map-indexed (fn [idx itm]
                   (concat (take col itm)
                           (list (nth ncol idx))
                           (drop (inc col) itm)))
                 scr)))

(defn- parse-line
  [l]
  (let [[cmd rowcol? & _ :as tokens] (clojure.string/split l #" ")]
    (condp = cmd
      "rect" (let [[c r] (->> (clojure.string/split (second tokens) #"x")
                                                 (map #(Integer/parseInt %)))]
                                  (partial rect c r))
      "rotate" (let [cr (->> (clojure.string/split (nth tokens 2) #"=")
                            second
                            Integer/parseInt)
                     x (Integer/parseInt (nth tokens 4))]
                 (partial (if (= "row" rowcol?) rotate-row rotate-col) cr x)))))

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (reduce (fn [scr instr] (instr scr)) (screen screen-size))
       flatten
       (filter true?)
       count))

(defn part-2
  [input]
  (->> input
       (map parse-line)
       (reduce (fn [scr instr] (instr scr)) (screen screen-size))
       (map (fn [row] (map #(if % \# \.) row)))
       (reduce (fn [s row] (str s "\n\t" (apply str row))) "\n")))