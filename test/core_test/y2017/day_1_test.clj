(ns core-test.y2017.day-1-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-1 :as day-1]))

(deftest part-1
  (testing "1"
    (is (= (day-1/part-1 "1122") 3)))
  (testing "2"
    (is (= (day-1/part-1 "1111") 4)))
  (testing "3"
    (is (= (day-1/part-1 "1234") 0)))
  (testing "4"
    (is (= (day-1/part-1 "91212129") 9))))

(deftest part-1
  (testing "1"
    (is (= (day-1/part-2 "1212") 6)))
  (testing "2"
    (is (= (day-1/part-2 "1221") 0)))
  (testing "3"
    (is (= (day-1/part-2 "123425") 4)))
  (testing "4"
    (is (= (day-1/part-2 "123123") 12)))
  (testing "5"
    (is (= (day-1/part-2 "12131415") 4))))
