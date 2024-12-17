(ns aoc.y2024.day-14
  (:require [clojure.set]))

(def ^:dynamic width 101)
(def ^:dynamic height 103)

(defn- parse-line
  [l]
  (->> l
       (re-seq #"\-?\d+")
       (map parse-long)
       (partition 2)))

(defn- get-robot-location
  [seconds [[x y] [vx vy]]]
  [(mod (+ x (* vx seconds)) width)
   (mod (+ y (* vy seconds)) height)])

(defn- get-quadrant
  [[x y]]
  [(< x (/ width 2)) (< y (/ height 2))])

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (map (partial get-robot-location 100))
       (remove #(or (= (first %) (quot width 2))
                    (= (second %) (quot height 2))))
       (group-by get-quadrant)
       vals
       (map count)
       (reduce *)))

(defn print-robots [robots]
  (let [max-row (inc (apply max (map first robots)))
        max-col (inc (apply max (map second robots)))
        grid (for [r (range max-row)]
               (for [c (range max-col)]
                 (if (some #(= [r c] %) robots)
                   "#"
                   ".")))]
    (doseq [row grid]
      (println (apply str row)))))

(defn part-2
  [input]
  (let [robots (map parse-line input)]
    (println robots)
    (loop [t 0
           min-t nil
           safety-factor Long/MAX_VALUE]
      (let [new-locations (map (partial get-robot-location t) robots)
            new-safety-factor (->> new-locations
                                   (remove #(or (= (first %) (quot width 2))
                                                (= (second %) (quot height 2))))
                                   (group-by get-quadrant)
                                   vals
                                   (map count)
                                   (reduce *))]
        (when (or (< new-safety-factor safety-factor) (= 100 t))
          (do (println "t=" t)
              (println "safety-factor=" new-safety-factor)
              (print-robots new-locations)))
        (if (>= t (* width height))
          min-t
          (recur (inc t)
                 (if (< new-safety-factor safety-factor) t min-t)
                 (if (< new-safety-factor safety-factor) new-safety-factor safety-factor)))))))
