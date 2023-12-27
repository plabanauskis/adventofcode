(ns aoc.y2015.day-8
  (:require [clojure.string :as str]))

(defn- decode
  [l]
  (let [l (apply str (->> l (drop 1) (drop-last)))]
    (-> l
        (str/replace #"\\\"" "\"")
        (str/replace #"\\x[0-9a-f-A-F]{2}" "+")
        (str/replace #"\\\\" "/"))))

(defn part-1
  [input]
  (let [orig-len (->> input
                      (map count)
                      (apply +))
        dec-len (->> input
                     (map decode)
                     (map count)
                     (apply +))]
    (- orig-len dec-len)))

(defn- encode
  [l]
  (str \"
       (-> l
           (str/replace #"\\" "\\\\\\\\")
           (str/replace #"\"" "\\\\\""))
       \"))

(defn part-2
  [input]
  (let [orig-len (->> input
                      (map count)
                      (apply +))
        enc-len (->> input
                     (map encode)
                     (map count)
                     (apply +))]
    (- enc-len orig-len)))