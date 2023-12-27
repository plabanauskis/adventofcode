(ns aoc.y2015.day-1)

(defn- up-down
  [floor direction]
  (case direction
    \( (inc floor)
    \) (dec floor)))

(defn part-1
  [input]
  (reduce up-down 0 input))

(defn- until-minus-first-floor
  [[index floor] direction]
  (let [new-floor (up-down floor direction)
        new [(inc index) new-floor]]
    (if (= -1 new-floor)
      (reduced new)
      new)))

(defn part-2
  [input]
  (first (reduce until-minus-first-floor [0 0] input)))