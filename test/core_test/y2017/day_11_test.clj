(ns core-test.y2017.day-11-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-11 :as day-11]))

(deftest part-1
  (testing "1"
    (is (= 3 (day-11/part-1 "ne,ne,ne")))
    (is (= 0 (day-11/part-1 "ne,ne,sw,sw")))
    (is (= 2 (day-11/part-1 "ne,ne,s,s")))
    (is (= 3 (day-11/part-1 "se,sw,se,sw,sw")))))
