(ns aoc.y2017.day-4)

(defn- no-repeating-words?
  [passphrase]
  (let [words (clojure.string/split passphrase #" ")]
    (= (count words) (count (set words)))))

(defn part-1
  [input]
  (->> input
       (filter no-repeating-words?)
       count))

(defn- no-anagrams?
  [passphrase]
  (let [words (clojure.string/split passphrase #" ")
        sorted-characters (map sort words)]
    (= (count words) (count (set sorted-characters)))))

(defn part-2
  [input]
  (->> input
       (filter no-anagrams?)
       count))