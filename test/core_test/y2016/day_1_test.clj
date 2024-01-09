(ns core-test.y2016.day-1-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-1 :as day-1]))

(deftest part-1
  (testing "1"
    (is (= (day-1/part-1 "R2, L3") 5)))
  (testing "2"
    (is (= (day-1/part-1 "R2, R2, R2") 2)))
  (testing "3"
    (is (= (day-1/part-1 "R5, L5, R5, R3") 12))))

(deftest part-2
  (testing "4"
    (is (= (day-1/part-2 "R8, R4, R4, R8") 4))))
