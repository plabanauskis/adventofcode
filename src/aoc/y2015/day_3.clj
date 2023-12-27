(ns aoc.y2015.day-3)

(defn- move
  [loc dir]
  (case dir
    \^ [(dec (first loc)) (second loc)]
    \v [(inc (first loc)) (second loc)]
    \< [(first loc) (dec (second loc))]
    \> [(first loc) (inc (second loc))]))

(defn- visit
  [visited direction]
  (let [new-location (move (:current visited) direction)]
    {:current new-location :all (conj (:all visited) new-location)}))

(defn part-1
  [input]
  (let [start [0 0]]
    (->> input
         (reduce visit {:current start :all #{start}})
         :all
         count)))

(defn- visit-with-robosanta
  [visited direction]
  (let [santas #{:santa :robosanta}
        current-santa (:turn visited)
        another-santa (first (remove #{current-santa} santas))
        new-location (move (get visited current-santa) direction)]
    (into {:all (conj (:all visited) new-location)}
          {current-santa new-location
           another-santa (get visited another-santa)
           :turn another-santa})))

(defn part-2
  [input]
  (let [start [0 0]
        santa start
        robosanta start]
    (->> input
         (reduce visit-with-robosanta {:santa santa :robosanta robosanta :turn :santa :all #{start}})
         :all
         count)))