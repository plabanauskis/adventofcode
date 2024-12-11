(ns aoc.y2024.day-4)

(defn- get-rows
  [input]
  (concat input (map clojure.string/reverse input)))

(defn- get-cols
  [input]
  (let [cols (apply map str input)]
    (concat cols (map clojure.string/reverse cols))))

(defn- diagonal-lines [input direction]
  (let [height (count input)
        width (count (first input))
        top-cols (if (= direction :right)
                   (range width)
                   (range (dec width) -1 -1))
        side-col (if (= direction :right) 0 (dec width))
        top-starts (map #(vector 0 %) top-cols)
        side-starts (map #(vector % side-col) (range 1 height))
        all-starts (concat top-starts side-starts)]
    (for [start all-starts]
      (let [[start-row start-col] start]
        (->> (range)
             (map (fn [i] [(+ start-row i)
                           (if (= direction :right)
                             (+ start-col i)
                             (- start-col i))]))
             (take-while (fn [[r c]]
                           (and (< r height)
                                (if (= direction :right)
                                  (< c width)
                                  (>= c 0)))))
             (map (fn [[r c]] (nth (nth input r) c)))
             (apply str))))))

(defn- right-bottom-diagonals [input]
  (diagonal-lines input :right))

(defn- left-bottom-diagonals [input]
  (diagonal-lines input :left))

(defn get-diagonals
  [input]
  (let [diagonals-rb (right-bottom-diagonals input)
        diagonals-lb (left-bottom-diagonals input)]
    (concat diagonals-rb
            (map clojure.string/reverse diagonals-rb)
            diagonals-lb
            (map clojure.string/reverse diagonals-lb))))

  (defn- get-all-lines
  [input]
  (concat (get-rows input)
          (get-cols input)
          (get-diagonals input)))

(defn- count-xmas
  [line]
  (count (re-seq #"XMAS" line)))

(defn part-1
  [input]
  (->> (get-all-lines input)
       (map count-xmas)
       (apply +)))

(defn- x-mas?
  [input [row col :as pos]]
  (let [input (vec input)]
    (when (= (get-in input pos) \A)
      (let [ms1 (sort [(get-in input [(dec row) (dec col)])
                       (get-in input [(inc row) (inc col)])])
            ms2 (sort [(get-in input [(dec row) (inc col)])
                       (get-in input [(inc row) (dec col)])])]
        (and (= ms1 [\M \S])
             (= ms2 [\M \S]))))))

(defn part-2
  [input]
  (let [positions (for [row (range 1 (dec (count input)))
                        col (range 1 (dec (count (first input))))]
                    [row col])]
    (->> positions
         (filter (partial x-mas? input))
         count)))
