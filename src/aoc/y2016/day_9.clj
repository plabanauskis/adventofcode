(ns aoc.y2016.day-9
  (:require [clojure.string]))

(defn part-1
  [input]
  (let [inp-len (count input)]
    (loop [i 0 cnt 0]
      (if (>= i inp-len)
        cnt
        (if (= \( (nth input i))
          (let [close-idx (clojure.string/index-of input \) i)
                marker (subs input (inc i) close-idx)
                [num-chars reps] (->> (clojure.string/split marker #"x")
                                      (map #(Integer/parseInt %)))]
            (recur (+ (inc close-idx) num-chars) (+ cnt (* num-chars reps))))
          (recur (inc i) (inc cnt)))))))

(defn- decompress
  [s]
  (let [inp-len (count s)]
    (loop [i 0 cnt 0]
      (if (>= i inp-len)
        cnt
        (if (= \( (nth s i))
          (let [close-idx (clojure.string/index-of s \) i)
                segment-idx (inc close-idx)
                marker (subs s (inc i) close-idx)
                [num-chars reps] (->> (clojure.string/split marker #"x")
                                      (map #(Integer/parseInt %)))
                decompressed-len (* reps (decompress (subs s segment-idx (+ segment-idx num-chars))))]
            (recur (+ segment-idx num-chars) (+ cnt decompressed-len)))
          (recur (inc i) (inc cnt)))))))

(defn part-2
  [input]
  (decompress input))