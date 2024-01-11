(ns aoc.y2016.core
  (:require [utils :refer [read-resource]]))

(defn- require-day-namespace [day]
  (let [namespace-symbol (symbol (str "aoc.y2016.day-" day))]
    (require namespace-symbol)))

(defn- solve-day [day]
  (require-day-namespace day)
  (let [input (read-resource (str "2016/" day ".txt"))
        input (if (and (seq input) (not (next input)))
                (first input)
                input)
        part-1 (ns-resolve 'aoc.y2016.core (symbol (str "aoc.y2016.day-" day "/part-1")))
        part-2 (ns-resolve 'aoc.y2016.core (symbol (str "aoc.y2016.day-" day "/part-2")))]
    (println "(AoC 2016) Day" day)
    (println "\tPart 1: " (part-1 input))
    (println "\tPart 2: " (part-2 input))))

(defn solve []
  ;(solve-day 1)
  ;(solve-day 2)
  ;(solve-day 3)
  ;(solve-day 4)
  ;(solve-day 5)
  ;(solve-day 6)
  ;(solve-day 7)
  ;(solve-day 8)
  ;(solve-day 9)
  ;(solve-day 10)
  ;(solve-day 11)
  ;(solve-day 12)
  ;(solve-day 13)
  ;(solve-day 14)
  ;(solve-day 15)
  ;(solve-day 16)
  ;(solve-day 17)
  ;(solve-day 18)
  ;(solve-day 19)
  ;(solve-day 20)
  ;(solve-day 21)
  ;(solve-day 22)
  (solve-day 23)
  ;(solve-day 24)
  ;(solve-day 25)
  )
