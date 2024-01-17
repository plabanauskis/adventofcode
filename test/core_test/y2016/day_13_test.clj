(ns core-test.y2016.day-13-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-13 :as day-13]))

(deftest part-1
  (testing "1"
    (with-bindings {#'day-13/destination [7 4]}
      (is (= (day-13/part-1 "10") 11)))))
