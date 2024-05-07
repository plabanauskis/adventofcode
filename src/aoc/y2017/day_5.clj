(ns aoc.y2017.day-5)

(defn- run
  [input modify-offset-fn]
  (let [offsets (vec (map #(Integer/parseInt %) input))
        num-offsets (count offsets)]
    (loop [offsets offsets
           i 0
           steps 0]
      (if (>= i num-offsets)
        steps
        (let [offset (nth offsets i)]
          (recur (update offsets i modify-offset-fn)
                 (+ i offset)
                 (inc steps)))))))

(defn part-1
  [input]
  (run input inc))

(defn part-2
  [input]
  (run input #(if (<= 3 %) (dec %) (inc %))))