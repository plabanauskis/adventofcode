(ns aoc.y2016.day-11
  (:require [clojure.string]
            [clojure.math.combinatorics :as comb]
            [clojure.set])
  (:import (clojure.lang PersistentQueue)))

(defn- parse-line
  [l]
  (->> l
       (re-seq #"\b(\w+)(?:-compatible)? (microchip|generator)\b")
       (map (fn [[_ group-el group-type]] [(keyword group-el) (keyword group-type)]))
       set))

(defn- goal-reached?
  [state]
  (every? empty? (butlast (:floors state))))

(defn- valid-floor?
  [floor]
  (let [generators (set (map first (filter #(= :generator (second %)) floor)))
        microchips (set (map first (filter #(= :microchip (second %)) floor)))]
    (or (empty? generators)
        (every? generators microchips))))

(defn- get-carriable-items
  [floor]
  (let [singles (filter (fn [items]
                          (let [[e t] (first items)]
                            (or (= :microchip t)
                                (not (floor [e :microchip])))))
                        (map vector floor))
        pairs (filter (fn [[a b]] (or (= (second a) (second b))
                                      (= (first a) (first b))))
                      (comb/combinations floor 2))]
    (set (concat singles pairs))))

(defn- valid-new-states
  ([floors curr-el next-el]
   (let [curr-floor (get floors curr-el)
         next-floor (get floors next-el)
         carriable-items (get-carriable-items curr-floor)]
     (for [items carriable-items
           :let [c-floor (clojure.set/difference curr-floor items)
                 n-floor (clojure.set/union next-floor (set items))]
           :when (and (valid-floor? c-floor) (valid-floor? n-floor))]
       {:floors (-> floors
                    (assoc curr-el c-floor)
                    (assoc next-el n-floor))
        :elevator next-el})))

  ([{:keys [floors elevator]}]
   (let [next-el (when (< elevator (dec (count floors))) (inc elevator))
         prev-el (when (pos? elevator) (dec elevator))]
     (concat (when next-el (valid-new-states floors elevator next-el))
             (when prev-el (valid-new-states floors elevator prev-el))))))

(defn- find-min-steps
  [floors]
  (let [initial-state {:floors floors :elevator 0}
        steps 0]
    (loop [queue (conj PersistentQueue/EMPTY [initial-state steps])
           visited #{initial-state}]
      (when-not (empty? queue)
        (let [[state steps] (peek queue)
              queue (pop queue)]
          (if (goal-reached? state)
            steps
            (let [new-states (valid-new-states state)
                  new-states (remove visited new-states)]
              (recur (into queue (map vector new-states (repeat (inc steps))))
                     (clojure.set/union visited (set new-states))))))))))

(defn part-1
  [input]
  (let [floors (vec (map parse-line input))]
    (find-min-steps floors)))

(defn part-2
  [input]
  (let [floors (vec (map parse-line input))
        extras #{[:elerium :generator] [:elerium :microchip] [:dilithium :generator] [:dilithium :microchip]}
        floors (assoc floors 0 (clojure.set/union (first floors) extras))]
    (find-min-steps floors)))