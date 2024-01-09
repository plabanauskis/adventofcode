(ns aoc.y2016.day-7
  (:require [clojure.string]))

(defn- parse-line
  [l]
  (let [l (str \] l)]
    (->> (clojure.string/split l #"\[")
         (map #(clojure.string/split % #"\]"))
         (apply map vector))))

(defn- abba?
  [[hyper super]]
  (letfn [(contains-abba? [s]
            (->> (partition 4 1 s)
                 (some (fn [[w x y z]]
                         (and (= w z) (= x y) (not= w x))))))]
    (and (some contains-abba? super)
         (not-any? contains-abba? hyper))))

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (filter abba?)
       count))

(defn- ssl?
  [[hyper super]]
  (let [abas (set (for [s super
                        [a b c] (partition 3 1 s)
                        :when (and (= a c) (not= a b))]
                    (str b a b)))]
    (some (fn [h] (some #(clojure.string/includes? h %) abas)) hyper)))

(defn part-2
  [input]
  (->> input
       (map parse-line)
       (filter ssl?)
       count))