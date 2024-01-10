(ns aoc.y2016.day-19)

(defn part-1
  [input]
  (let [n (Integer/parseInt input)]
    (inc (* 2 (- n (Integer/highestOneBit n))))))

(defn part-2
  [input]
  (let [n (Integer/parseInt input)]
    (- n (loop [i 1]
           (if (>= (* 3 i) n)
             i
             (recur (* 3 i)))))))