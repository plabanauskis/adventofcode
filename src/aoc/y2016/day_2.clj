(ns aoc.y2016.day-2)

(defn- parse-line
  [l]
  (map (comp keyword str) l))

(defn- find-key-pt1
  [curr instr]
  (case instr
    :U (let [nxt (- curr 3)] (if (< nxt 1) curr nxt))
    :D (let [nxt (+ curr 3)] (if (> nxt 9) curr nxt))
    :L (let [nxt (dec curr)] (if (#{0 3 6} nxt) curr nxt))
    :R (let [nxt (inc curr)] (if (#{4 7 10} nxt) curr nxt))))

(defn- find-code
  [find-key-fn curr instr]
  (let [new (reduce find-key-fn (last curr) instr)]
    (conj curr new)))

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (reduce (partial find-code find-key-pt1) [5])
       rest
       (apply str)))

(defn- find-key-pt2
  [curr instr]
  (case instr
    :U (case curr
         (\6 \3) (char (+ (/ (- (int curr) 48) 3) 48))
         (\7 \8) (char (+ (- (- (int curr) 48) 4) 48))
         (\A \B \C) (char (+ (- (int curr) 59) 48))
         \D \B
         curr)
    :D (case curr
         \B \D
         (\6 \7 \8) (char (+ (- (int curr) 48) 59))
         (\2 \1) (char (+ (* (- (int curr) 48) 3) 48))
         (\3 \4) (char (+ (+ (- (int curr) 48) 4) 48))
         curr)
    :L (case curr
         (\3 \4 \6 \7 \8 \9) (char (+ (dec (- (int curr) 48)) 48))
         (\B \C) (char (dec (int curr)))
         curr)
    :R (case curr
         (\2 \3 \5 \6 \7 \8) (char (+ (inc (- (int curr) 48)) 48))
         (\A \B) (char (inc (int curr)))
         curr)))

(defn part-2
  [input]
  (->> input
       (map parse-line)
       (reduce (partial find-code find-key-pt2) [\5])
       rest
       (apply str)))