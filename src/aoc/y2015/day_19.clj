(ns aoc.y2015.day-19
  (:require [clojure.string :as str]))

(defn- parse-input
  [input]
  (let [replacements (->> input
                          (drop-last 2)
                          (map #(str/split % #" => ")))]
    [replacements (last input)]))

(defn- replace-all
  [molecule [from to]]
  (loop [ix 0
         replaced []]
    (if-let [replace-at (str/index-of molecule to ix)]
      (recur (inc replace-at)
             (conj replaced (str (subs molecule 0 replace-at) from (subs molecule (+ replace-at (count to))))))
      replaced)))

(defn part-1
  [input]
  (let [[replacements molecule] (parse-input input)]
    (->> replacements
         (map (partial replace-all molecule))
         flatten
         set
         count)))

(defn part-2
  [input]
  (let [[replacements molecule] (parse-input input)]
    (loop [molecule molecule
           steps 0]
      (if (= "e" molecule)
        steps
        (let [shortest (->> replacements
                            (map (partial replace-all molecule))
                            flatten
                            (apply min-key count))]
          (recur shortest
                 (inc steps)))))))