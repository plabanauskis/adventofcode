(ns aoc.y2017.day-15)

(def factors [16807 48271])
(def multiples [4 8])
(def divider 2147483647)

(defn- parse-line
  [l]
  (let [[_ num] (re-matches #".+ (\d+)" l)]
    (Integer/parseInt num)))

(defn- gen-next-pt1
  [value factor]
  (rem (* value factor) divider))

(defn part-1
  [input]
  (let [[a b] (map parse-line input)]
    (->> [a b]
         (iterate #(map gen-next-pt1 % factors))
         (drop 1)
         (take 40000000)
         (map (fn [generators]
                (map #(bit-and % 0xFFFF) generators)))
         (filter #(apply = %))
         count)))

(defn- gen-next-pt2
  [value factor multiple]
  (->> value
       (iterate #(rem (* % factor) divider))
       (drop 1)
       (drop-while #(not (zero? (mod % multiple))))
       first))

(defn part-2
  [input]
  (let [[a b] (map parse-line input)]
    (->> [a b]
         (iterate #(map gen-next-pt2 % factors multiples))
         (drop 1)
         (take 5000000)
         (map (fn [generators]
                (map #(bit-and % 0xFFFF) generators)))
         (filter #(apply = %))
         count)))
