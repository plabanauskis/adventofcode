(ns aoc.y2017.day-6)

(defn- parse-line
  [l]
  (->> (clojure.string/split l #"\t")
       (map #(Integer/parseInt %))
       vec))

(defn- largest-bank
  [memory]
  (let [max-val (apply max memory)]
    (.indexOf memory max-val)))

(defn- get-distribution
  [block-count item-count]
  (let [base (quot item-count block-count)
        remainder (rem item-count block-count)
        base-distribution (repeat block-count base)
        additional-items (concat (repeat remainder 1)
                                 (repeat (- block-count remainder) 0))]
    (map + base-distribution additional-items)))

(defn- redistribute
  [memory]
  (let [mem-size (count memory)
        max-block-ix (largest-bank memory)
        max-block (get memory max-block-ix)
        memory (assoc memory max-block-ix 0)
        redistribution (get-distribution mem-size max-block)
        redistribution (vec (concat redistribution redistribution))
        redistribution (subvec redistribution (dec (- mem-size max-block-ix))
                               (+ mem-size (dec (- mem-size max-block-ix))))]
    (vec (map + memory redistribution))))

(defn part-1
  [input]
  (let [memory (parse-line input)
        redistributions (iterate redistribute memory)]
    (loop [remaining redistributions
           seen #{}
           count 0]
      (let [distribution (first remaining)]
        (if (seen distribution)
          count
          (recur (rest remaining) (conj seen distribution) (inc count)))))))

(defn part-2
  [input]
  (let [memory (parse-line input)
        redistributions (iterate redistribute memory)]
    (loop [remaining redistributions
           seen {}
           i 0]
      (let [distribution (first remaining)]
        (if-let [prev-i (seen distribution)]
          (- i prev-i)
          (recur (rest remaining) (assoc seen distribution i) (inc i)))))))