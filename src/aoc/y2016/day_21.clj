(ns aoc.y2016.day-21
  (:require [clojure.string]
            [clojure.math.combinatorics :as comb]))

(def ^:dynamic password-pt1 "abcdefgh")
(def ^:dynamic password-pt2 "fbgdceah")

(defn- index-of [coll item]
  (first (keep-indexed (fn [idx v] (when (= v item) idx)) coll)))

(defn- swap-position
  [x y s]
  (let [at-x (get s x)]
    (-> s
        (assoc x (get s y))
        (assoc y at-x))))

(defn- swap-letter
  [x y s]
  (let [idx-x (index-of s x)
        idx-y (index-of s y)
        s (vec (concat (take idx-x s) [y] (drop (inc idx-x) s)))]
    (vec (concat (take idx-y s) [x] (drop (inc idx-y) s)))))

(defn- rotate-left
  [x s]
  (->> (concat s s)
       (drop (mod x (count s)))
       (take (count s))
       vec))

(defn- rotate-right
  [x s]
  (->> (concat s s)
       (drop (- (count s) (mod x (count s))))
       (take (count s))
       vec))

(defn- rotate-based
  [x s]
  (let [idx-x (index-of s x)]
    (rotate-right (+ (inc idx-x) (if (>= idx-x 4) 1 0)) s)))

(defn- move
  [x y s]
  (let [at-x (get s x)
        s (vec (concat (take x s) (drop (inc x) s)))]
    (vec (concat (take y s) [at-x] (drop y s)))))

(defn- reverse-positions
  [x y s]
  (vec (concat (take x s)
               (reverse (take (inc (- y x)) (drop x s)))
               (drop (inc y) s))))

(defn- parse-line
  [l]
  (cond
    (clojure.string/starts-with? l "swap position")
    (let [[_ x y] (re-matches #".+ (\d+) .+ (\d+)" l)
          x (Integer/parseInt x)
          y (Integer/parseInt y)]
      (partial swap-position x y))

    (clojure.string/starts-with? l "swap letter")
    (let [[_ x y] (re-matches #".+letter (.) .+ (.)" l)
          x (first x)
          y (first y)]
      (partial swap-letter x y))

    (clojure.string/starts-with? l "rotate left")
    (let [[_ x] (re-matches #".+ (\d+) .+" l)
          x (Integer/parseInt x)]
      (partial rotate-left x))

    (clojure.string/starts-with? l "rotate right")
    (let [[_ x] (re-matches #".+ (\d+) .+" l)
          x (Integer/parseInt x)]
      (partial rotate-right x))

    (clojure.string/starts-with? l "rotate based")
    (let [[_ x] (re-matches #".+(.)" l)
          x (first x)]
      (partial rotate-based x))

    (clojure.string/starts-with? l "reverse")
    (let [[_ x y] (re-matches #".+? (\d+) .+ (\d+)" l)
          x (Integer/parseInt x)
          y (Integer/parseInt y)]
      (partial reverse-positions x y))

    (clojure.string/starts-with? l "move")
    (let [[_ x y] (re-matches #".+? (\d+) .+ (\d+)" l)
          x (Integer/parseInt x)
          y (Integer/parseInt y)]
      (partial move x y))))

(defn part-1
  [input]
  (let [password (vec password-pt1)
        fs (map parse-line input)]
    (->> password
         ((apply comp (reverse fs)))
         (apply str))))

(defn part-2
  [input]
  (let [password (vec password-pt2)
        fs (map parse-line input)
        candidates (->> password
                        (comb/permutations)
                        (map vec))]
    (->> candidates
         (filter (fn [p]
                   (= password-pt2 (->> p
                                        ((apply comp (reverse fs)))
                                        (apply str)))))
         first
         (apply str))))