(ns aoc.y2017.day-13
  (:require [clojure.set]))

(defn- parse-line [l]
  (->> l
       (re-seq #"\d+")
       (mapv #(Integer/parseInt %))))

(defn- get-scan-periods
  [firewall]
  (let [max-depth (->> firewall keys (apply max))
        zero-vector (vec (repeat (inc max-depth) nil))]
    (reduce-kv #(assoc %1 %2 (* 2 (dec %3)))
               zero-vector
               firewall)))

(defn part-1
  [input]
  (let [firewall (->> input
                      (map parse-line)
                      (into {}))
        scan-periods (get-scan-periods firewall)]
    (reduce-kv
      (fn [severity i period]
        (if (and period (or (zero? period)
                            (zero? (mod i period))))
          (+ severity (* i (get firewall i)))
          severity))
      0
      scan-periods)))

(defn caught?
  [scan-periods delay]
  (some (fn [[i period]]
          (and period (or (zero? period)
                          (zero? (mod (+ i delay) period)))))
        (map-indexed vector scan-periods)))

(defn part-2
  [input]
  (let [firewall (->> input
                      (map parse-line)
                      (into {}))
        scan-periods (get-scan-periods firewall)]
    (loop [delay 0]
      (if (not (caught? scan-periods delay))
        delay
        (recur (inc delay))))))
