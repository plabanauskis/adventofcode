(ns aoc.y2015.day-4
  (:import (java.security MessageDigest)))

(defn- md5-hash [s]
  (->> s
       .getBytes
       (.digest (MessageDigest/getInstance "MD5"))
       (map byte)
       (map #(format "%02x" %))
       (apply str)))

(defn- solve
  [input n]
  (->> (iterate inc 1)
       (drop-while (fn [x]
                     (->> x
                          (str input)
                          md5-hash
                          ((partial #(subs % 0 n)))
                          (not= "00000"))))
       first))

(defn part-1
  [input]
  (solve input 5))

(defn part-2
  [input]
  (solve input 6))