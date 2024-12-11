(ns aoc.y2024.day-6)

(defn- parse-input
  [input]
  (mapv vec input))

(defn- find-guard
  [area]
  (->> area
       (keep-indexed (fn [i row]
                       (let [guard-position (.indexOf row \^)]
                         (when (>= guard-position 0)
                           [i guard-position]))))
       first))

(defn- step
  [area [row col :as guard-position] [vertical horizontal :as direction]]
  (let [test-row (+ row vertical)
        test-col (+ col horizontal)]
    (if (= \# (get-in area [test-row test-col]))
      ; turn
      {:guard-position guard-position
       :direction [horizontal (- vertical)]}

      ; step
      {:guard-position [(+ row vertical) (+ col horizontal)]
       :direction direction})))

(defn- out-of-bounds?
  [area [row col]]
  (or (< row 0)
      (>= row (count area))
      (< col 0)
      (>= col (count (first area)))))

(defn part-1
  [input]
  (let [area (parse-input input)]
    (loop [current-guard-position (find-guard area)
           direction [-1 0]
           visited #{}]
      (if (out-of-bounds? area current-guard-position)
        (count visited)
        (let [{:keys [guard-position direction]}
              (step area current-guard-position direction)]
          (recur guard-position
                 direction
                 (conj visited current-guard-position)))))))

(defn- place-obstructions
  [area]
  (for [row (range (count area))
        col (range (count (first area)))
        :when (not (#{\# \^} (get-in area [row col])))]
    (assoc-in area [row col] \#)))

(defn- loop?
  [area]
  (loop [current-guard-position (find-guard area)
         current-direction [-1 0]
         visited #{}]
    (cond (visited [current-guard-position current-direction]) true
          (out-of-bounds? area current-guard-position) false
          :else (let [{:keys [guard-position direction]}
                      (step area current-guard-position current-direction)]
                  (recur guard-position
                         direction
                         (conj visited [current-guard-position current-direction]))))))

(defn part-2
  [input]
  (let [area (parse-input input)
        areas-with-obstructions (place-obstructions area)]
    (->> areas-with-obstructions
         (pmap loop?)
         (filter identity)
         count)))
