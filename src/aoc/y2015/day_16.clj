(ns aoc.y2015.day-16
  (:require [clojure.string :as str]))

(def ^:private ticker-tape
  {"children" 3
   "cats" 7
   "samoyeds" 2
   "pomeranians" 3
   "akitas" 0
   "goldfish" 5
   "trees" 5
   "cars" 2
   "perfumes" 1})

(defn- parse-line
  [l]
  (let [parsed (-> l
                   (str/replace-first #":" ",")
                   (str/replace "Sue" "Sue:")
                   (str/split #"(: |, )"))]
    (update-vals (apply hash-map parsed) #(Integer/parseInt %))))

(defn- matches-pt1?
  [ticker-tape sue]
  (every? #(or (= (ticker-tape (key %)) (val %))
               (and (not (ticker-tape (key %)))
                    (zero? (val %))))
          (dissoc sue "Sue")))

(defn part-1
  [input]
  (get (->> input
            (map parse-line)
            (filter (partial matches-pt1? ticker-tape))
            first)
       "Sue"))

(defn- matches-pt2?
  [ticker-tape sue]
  (letfn [(matches? [dependant]
            (let [f (case (key dependant)
                      ("cats" "trees") <
                      ("pomeranians" "goldfish") >
                      =)]
              (or (f (ticker-tape (key dependant)) (val dependant))
                  (and (not (ticker-tape (key dependant)))
                       (zero? (val dependant))))))]
    (every? matches? (dissoc sue "Sue"))))

(defn part-2
  [input]
  (get (->> input
            (map parse-line)
            (filter (partial matches-pt2? ticker-tape))
            first)
       "Sue"))