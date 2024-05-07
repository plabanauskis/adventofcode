(ns aoc.y2017.day-3)

(defn part-1
  [input]
  (let [input (Integer/parseInt input)
        side-length (->> (iterate #(+ 2 %) 1)
                         (map #(vector % (* % %)))
                         (drop-while #(< (second %) input))
                         ffirst)
        layer (quot side-length 2)
        midpoints (take 4
                        (iterate #(- % (dec side-length))
                                 (- (* side-length side-length) layer)))
        input-midpoint (->> midpoints
                            (filter #(<= (- % layer) input (+ % layer)))
                            first)
        distance-to-midpoint (abs (- input-midpoint input))]
    (+ distance-to-midpoint layer)))

(defn- neighbors
  [spiral position]
  (let [neighbor-positions (for [x [-1 0 1]
                                 y [-1 0 1]
                                 :when (or (not (zero? x)) (not (zero? y)))]
                             [(+ (first position) x) (+ (second position) y)])]
    (map #(get-in spiral %) neighbor-positions)))

(defn part-2
  [input]
  (let [input (Integer/parseInt input)
        spiral-side-length (->> input Math/log10 Math/ceil (Math/pow 10) int)
        spiral (vec (repeat spiral-side-length (vec (repeat spiral-side-length 0))))
        position [(/ spiral-side-length 2) (/ spiral-side-length 2)]
        spiral (assoc-in spiral position 1)
        it #(iterate inc 1)
        step-count (interleave (it) (it))
        directions (cycle [#(update % 1 inc)
                           #(update % 0 dec)
                           #(update % 1 dec)
                           #(update % 0 inc)])
        steps (mapcat #(repeat %1 %2) step-count directions)]
    (reduce (fn [[spiral position] step]
              (let [position (step position)
                    new (apply + (neighbors spiral position))]
                (if (> new input)
                  (reduced new)
                  [(assoc-in spiral position new) position])))
            [spiral position]
            steps)))