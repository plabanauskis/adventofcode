(ns aoc.y2017.day-10
  (:require [clojure.string]))

(def ^:dynamic num-elements 256)

(defn- move-index-left
  [i len]
  (if (zero? i)
    (- len 1)
    (dec i)))

(defn- move-index-right
  [i len]
  (if (== i (dec len))
    0
    (inc i)))

(defn- swap
  [coll i j]
  (assoc coll
    i (nth coll j)
    j (nth coll i)))

(defn- twist
  [[elements curr skip] len]
  (let [num-elements (count elements)
        mid-ix (mod (+ curr (quot len 2)) num-elements)
        twisted-elements (loop [elements elements
                                left-ix (move-index-left mid-ix num-elements)
                                right-ix (if (even? len) mid-ix
                                                         (move-index-right mid-ix num-elements))
                                rem-len (- len (if (even? len) 1 2))]
                           (if (neg? rem-len)
                             elements
                             (recur (swap elements left-ix right-ix)
                                    (move-index-left left-ix num-elements)
                                    (move-index-right right-ix num-elements)
                                    (- rem-len 2))))]
    [twisted-elements (mod (+ curr len skip) num-elements) (inc skip)]))

(defn- parse-input
  [input]
  (->> (clojure.string/split input #",")
       (map #(Integer/parseInt %))))

(defn part-1
  [input]
  (->> input
       parse-input
       (reduce twist [(vec (range num-elements)) 0 0])
       first
       (take 2)
       (apply *)))

(defn- format-bytes
  [bytes]
  (map #(format "%02x" %) bytes))

(defn- calculate-hash
  [sequence format-bytes-fn]
  (->> sequence
       (partition 16)
       (map #(apply bit-xor %))
       format-bytes-fn
       clojure.string/join))

(defn knot-hash
  [s format-bytes-fn]
  (let [len (vec (concat (map int s) [17 31 73 47 23]))
        rounds 64]
    (loop [i 0
           sequence (vec (range num-elements))
           curr 0
           skip 0]
      (if (= rounds i)
        (calculate-hash sequence format-bytes-fn)
        (let [[twisted new-curr new-skip] (reduce twist [sequence curr skip] len)]
          (recur (inc i) twisted new-curr new-skip))))))

(defn part-2
  [input]
  (knot-hash input format-bytes))
