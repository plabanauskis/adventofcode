(ns core-test.y2015.day-1-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-1 :as day-1]))

(deftest part-1
  (testing "1"
    (is (= (day-1/part-1 "(())") 0)))
  (testing "2"
    (is (= (day-1/part-1 "()()") 0)))
  (testing "3"
    (is (= (day-1/part-1 "(((") 3)))
  (testing "4"
    (is (= (day-1/part-1 "(()(()(") 3)))
  (testing "5"
    (is (= (day-1/part-1 "))(((((") 3)))
  (testing "6"
    (is (= (day-1/part-1 "())") -1)))
  (testing "7"
    (is (= (day-1/part-1 "))(") -1)))
  (testing "8"
    (is (= (day-1/part-1 ")))") -3)))
  (testing "9"
    (is (= (day-1/part-1 ")())())") -3))))

(deftest part-2
  (testing "10"
    (is (= (day-1/part-2 ")") 1)))
  (testing "11"
    (is (= (day-1/part-2 "()())") 5))))
