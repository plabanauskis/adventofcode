(ns core-test.y2015.day-1-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-1 :as day-1]))

(deftest part-1
  (testing "1"
    (is (= 0 (day-1/part-1 "(())"))))
  (testing "2"
    (is (= 0 (day-1/part-1 "()()"))))
  (testing "3"
    (is (= 3 (day-1/part-1 "((("))))
  (testing "4"
    (is (= 3 (day-1/part-1 "(()(()("))))
  (testing "5"
    (is (= 3 (day-1/part-1 "))((((("))))
  (testing "6"
    (is (= -1 (day-1/part-1 "())"))))
  (testing "7"
    (is (= -1 (day-1/part-1 "))("))))
  (testing "8"
    (is (= -3 (day-1/part-1 ")))"))))
  (testing "9"
    (is (= -3 (day-1/part-1 ")())())")))))

(deftest part-2
  (testing "10"
    (is (= 1 (day-1/part-2 ")"))))
  (testing "11"
    (is (= 5 (day-1/part-2 "()())")))))
