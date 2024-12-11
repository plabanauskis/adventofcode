(ns aoc.y2017.day-18)

(defn part-1
  [input]
  (let [steps (Integer/parseInt input)]
    (loop [i 1
           curr-ix 0
           buffer '(0)]
      (if (> i 2017)
        (nth buffer (inc curr-ix))
        (let [new-curr-ix (inc (mod (+ curr-ix steps) (count buffer)))]
          (recur (inc i)
                 new-curr-ix
                 (concat (take new-curr-ix buffer)
                         [i]
                         (drop new-curr-ix buffer))))))))

(defn part-2
  [input]
  (let [steps (Integer/parseInt input)]
    (loop [i 2
           curr-ix 1
           second-item 1]
      (if (> i 50000000)
        second-item
        (let [new-curr-ix (inc (mod (+ curr-ix steps) i))]
          (recur (inc i)
                 new-curr-ix
                 (if (= new-curr-ix 1)
                   i
                   second-item)))))))
