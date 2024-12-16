(ns aoc.y2024.day-13)

(defn parse-input
  [input]
  (->> (concat input [nil])
       (partition 4 4)
       (map (fn [[a b prize _]]
              {:a     (mapv parse-long (re-seq #"\d+" a))
               :b     (mapv parse-long (re-seq #"\d+" b))
               :prize (mapv parse-long (re-seq #"\d+" prize))}))))

(defn- calculate-tokens
  [{[ax ay] :a [bx by] :b [px py] :prize}]
  (let [[m-bx m-by] [(* bx ay) (* by ax)]
        [m-px m-py] [(* px ay) (* py ax)]
        b-times (/ (- m-py m-px) (- m-by m-bx))
        a-times (/ (- px (* bx b-times)) ax)]
    (when (and (int? a-times)
               (int? b-times))
      (+ (* 3 a-times) b-times))))

(defn part-1
  [input]
  (->> input
       parse-input
       (map calculate-tokens)
       (remove nil?)
       (apply +)))

(defn part-2
  [input]
  (->> input
       parse-input
       (map (fn [machine]
              (update machine :prize (partial mapv #(+ 10000000000000 %)))))
       (map calculate-tokens)
       (remove nil?)
       (apply +)))
