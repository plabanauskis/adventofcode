(ns aoc.y2015.day-15)

(defn- parse-line
  [l]
  (let [matches (re-matches #".+? (-?\d+?).+? (-?\d+?).+? (-?\d+?).+? (-?\d+?).+? (-?\d+?)" l)]
    (map #(Integer/parseInt %) (drop 1 matches))))

(defn- replace-at-index [s idx new-val]
  (map-indexed (fn [i val] (if (= i idx) new-val val)) s))

(defn- distribute-units [t n]
  "Distributes `t` types of objects across `n` possible units where each unit
  is taken at least once.
  For example, if t = 3 and n = 10, then distributions would look like
  [8, 1, 1], [7, 2, 1], ..., [1, 1, 8]."
  (let [max-units (- n (dec t))]
    (letfn [(next-units [prev ti]
              (if (= [2 (dec max-units)] (take-last 2 prev)) ; if [1, 1, 1, 2, 95] = 100 (base case)
                (list (concat (repeat (dec t) 1) [max-units])) ; then [1, 1, 1, 1, 96] = 100 is the last one
                (lazy-seq
                  (let [next-ti (or (when (< (inc ti) (count prev))
                                      (inc ti))
                                    (->> prev
                                         butlast
                                         (map-indexed vector)
                                         (filter #(> (second %) 1))
                                         last
                                         first))]          ; index of first >1 item
                    (when next-ti
                      (if (< next-ti ti)
                        (let [
                              tmp (concat (take next-ti prev)
                                          [(dec (nth prev next-ti))]
                                          [0]               ; placeholder item
                                          (repeat (- t (+ 2 next-ti)) 1))
                              next (replace-at-index tmp (inc next-ti) (- n (apply + tmp)))]
                          (cons next (next-units next (inc next-ti))))
                        (let [next (concat (take ti prev)
                                           [(dec (nth prev ti))]
                                           [(inc (nth prev (inc ti)))]
                                           (repeat (- t (+ 2 ti)) 1))]
                          (cons next (next-units next next-ti)))))))))]
      (let [current (cons max-units (repeat (dec t) 1))]
        (concat [current] (next-units current 0))))))

(defn- calculate-score
  [ingredients units]
  (->> (map (fn [u props] (map #(* u %) props)) units ingredients)
       (apply map vector)
       (map #(apply + %))
       (map #(if (neg? %) 0 %))
       (reduce * 1)))

(defn part-1
  [input]
  (let [ingredients (->> input
                         (map parse-line)
                         (map butlast))]
    (->> (distribute-units (count ingredients) 100)
         (map (partial calculate-score ingredients))
         (apply max))))

(defn- total-calories
  [ingredients units]
  (->> (map (fn [u props] (map #(* u %) props)) units ingredients)
       (map last)
       (apply +)))

(defn part-2
  [input]
  (let [ingredients-full (map parse-line input)
        ingredients (map butlast ingredients-full)]
    (->> (distribute-units (count ingredients-full) 100)
         (filter #(= 500 (total-calories ingredients-full %)))
         (map (partial calculate-score ingredients))
         (apply max))))