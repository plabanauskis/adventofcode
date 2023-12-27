(ns aoc.y2015.day-5
  (:require [clojure.string :as str]))

(defn- nice-part-1?
  [s]
  (letfn [(>-two-vowels [s]
            (-> (filter #{\a \e \i \o \u} s)
                count
                (>= 3)))
          (two-in-a-row [s]
            (->> (partition 2 1 s)
                (some #(apply = %))))
          (contains-bad-strings [s]
            (some #(str/includes? s %) ["ab" "cd" "pq" "xy"]))]
    (and (>-two-vowels s)
         (two-in-a-row s)
         (not (contains-bad-strings s)))))

(defn part-1
  [input]
  (->> input
       (filter nice-part-1?)
       count))

(defn- nice-part-2?
  [s]
  (letfn [(pair-contained-twice [s]
            (->> (partition 2 1 s)
                 (map (partial apply str))
                 (some (fn [x]
                         (let [i1 (str/index-of s x)
                               i2 (str/last-index-of s x)]
                           (and (not= i1 i2)
                                (> (- i2 i1) 1)))))))
          (letter-repeats-with-gap [s]
            (->> (partition 3 1 s)
                 (some (fn [[a b c]] (and (= a c) (not= a b))))))]
    (and (pair-contained-twice s)
         (letter-repeats-with-gap s))))

(defn part-2
  [input]
  (->> input
       (filter nice-part-2?)
       count))