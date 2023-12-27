(ns aoc.y2015.day-11)

(defn- next-password
  [p]
  (letfn [(inc-char [{:keys [pw carry]} c]
            (if carry
              (let [new-c (-> c int inc)]
                (if (> new-c (int \z))
                  {:pw (str pw \a) :carry true}
                  {:pw (str pw (char new-c)) :carry false}))
              {:pw (str pw c) :carry false}))]
    (->> p
         reverse
         (reduce inc-char {:pw "" :carry true})
         :pw
         reverse
         (apply str))))

(defn- valid-password?
  [p]
  (and (->> p
            (partition 3 1)
            (some #(= (+ 2 (int (first %))) (+ 1 (int (second %))) (int (last %)))))
       (not (some #{\i \o \l} p))
       (let [[_ x y] (re-matches #".*(.)\1.*(.)\2.*" p)]
         (not= x y))))

(defn part-1
  [input]
  (->> input
       (iterate next-password)
       (drop 1)
       (drop-while (complement valid-password?))
       first))

(defn part-2
  [input]
  (-> input part-1 part-1))