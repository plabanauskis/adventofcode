(ns core-test.y2017.day-1-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-1 :as day-1]))

(deftest part-1
  (testing "1"
    (is (= 3 (day-1/part-1 "1122"))))
  (testing "2"
    (is (= 4 (day-1/part-1 "1111"))))
  (testing "3"
    (is (= 0 (day-1/part-1 "1234"))))
  (testing "4"
    (is (= 9 (day-1/part-1 "91212129")))))

(deftest part-1
  (testing "1"
    (is (= 6 (day-1/part-2 "1212"))))
  (testing "2"
    (is (= 0 (day-1/part-2 "1221"))))
  (testing "3"
    (is (= 4 (day-1/part-2 "123425"))))
  (testing "4"
    (is (= 12 (day-1/part-2 "123123"))))
  (testing "5"
    (is (= 4 (day-1/part-2 "12131415")))))
