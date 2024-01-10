(ns aoc.y2016.day-14
  (:require [utils :refer [md5-hash]]))

(defn- key?
  [[h & hs]]
  (let [h (first h)
        triple-char (->> (partition 3 1 h)
                         (filter (fn [xs] (apply = xs)))
                         ffirst)]
    (when triple-char
      (->> (map first hs)
           (map #(partition 5 1 %))
           (some (fn [partitions] (some (fn [xs] (apply = (conj xs triple-char))) partitions)))))))

(defn part-1
  [input]
  (->> (iterate inc 0)
       (map (fn [x] [(str input x) x]))
       (pmap (fn [[s idx]] [(md5-hash s) idx]))
       (partition 1001 1)
       (filter key?)
       (drop 63)
       ffirst
       second))

(defn part-2
  [input]
  (->> (iterate inc 0)
       (map (fn [x] [(str input x) x]))
       (pmap (fn [[s idx]] [((apply comp (repeat 2017 md5-hash)) s) idx]))
       (partition 1001 1)
       (filter key?)
       (drop 63)
       ffirst
       second))