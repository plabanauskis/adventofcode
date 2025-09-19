(ns main
  (:require [utils :refer [read-resource]]
            [clojure.java.io :as io]))

(defn- get-resource-path
  [year day]
  (str year "/" day ".txt"))

(defn- require-day-namespace
  [year day]
  (let [namespace-symbol (symbol (str "aoc.y" year ".day-" day))]
    (require namespace-symbol)))

(defn- solve-day
  [year day]
  (require-day-namespace year day)
  (let [input (read-resource (get-resource-path year day))
        input (if (and (seq input) (not (next input)))
                (first input)
                input)
        ns-str (str "aoc.y" year ".day-" day)
        part-1 (ns-resolve (symbol ns-str) 'part-1)
        part-2 (ns-resolve (symbol ns-str) 'part-2)]
    (println (str "(AoC " year ") Day " day))
    (when part-1
      (println "\tPart 1: " (part-1 input)))
    (when part-2
      (println "\tPart 2: " (part-2 input)))))

(defn- day-exists?
  [year day]
  (let [resource-path (get-resource-path year day)]
    (some? (io/resource resource-path))))

(defn -main
  []
  (let [year (System/getenv "AOC_YEAR")
        day (System/getenv "AOC_DAY")]
    (cond
      (and year day)
      (let [day-num (Integer/parseInt day)]
        (println (str "Running AoC " year " Day " day-num))
        (if (day-exists? year day-num)
          (solve-day year day-num)
          (println (str "Day " day-num " not found (no input file)"))))

      :else
      (println "No configuration found. Set AOC_YEAR and AOC_DAY environment variables.")))

  (shutdown-agents))
