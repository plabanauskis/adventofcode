(ns core-test.y2016.day-1-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-1 :as day-1]))

(deftest part-1
  (testing "1"
    (is (= 5 (day-1/part-1 "R2, L3"))))
  (testing "2"
    (is (= 2 (day-1/part-1 "R2, R2, R2"))))
  (testing "3"
    (is (= 12 (day-1/part-1 "R5, L5, R5, R3")))))

(deftest part-2
  (testing "4"
    (is (= 4 (day-1/part-2 "R8, R4, R4, R8")))))
