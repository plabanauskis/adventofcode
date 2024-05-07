(ns core-test.y2017.day-3-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-3 :as day-3]))

(deftest part-1
  (testing "1"
    (is (= (day-3/part-1 "1") 0))
    (is (= (day-3/part-1 "12") 3))
    (is (= (day-3/part-1 "23") 2))
    (is (= (day-3/part-1 "1024") 31))))
