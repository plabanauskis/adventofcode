(ns aoc.y2016.day-4
  (:require [clojure.string]))

(defn- parse-line
  [l]
  (let [[name id checksum] (->> (re-matches #"(.+)-(\d+)\[(\w+)\]" l)
                                (drop 1))]
    [name (Integer/parseInt id) checksum]))

(defn- real?
  [[name _ chk]]
  (->> (clojure.string/replace name #"-" "")
       frequencies
       (map (fn [[k v]] [v k]))
       (sort (fn [[f1 l1] [f2 l2]]
               (let [freq-cmp (compare f2 f1)]
                 (if (zero? freq-cmp)
                   (compare l1 l2)
                   freq-cmp))))
       (take (count chk))
       (map second)
       set
       (= (set chk))))

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (filter real?)
       (map second)
       (apply +)))

(defn- decipher-name
  [[name id _]]
  (->> name
       (map (fn [l]
              (if (= l \-)
                \space
                (let [nl (+ (int l) (mod id 26))]
                  (if (<= nl (int \z))
                    (char nl)
                    (char (- nl 26)))))))
       (apply str)))

(defn part-2
  [input]
  (->> input
       (map parse-line)
       (filter real?)
       (map #(vector (second %) (decipher-name %)))
       (filter #(= "northpole object storage" (second %)))
       ffirst))