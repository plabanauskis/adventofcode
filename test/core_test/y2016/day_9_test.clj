(ns core-test.y2016.day-9-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-9 :as day-9]))

(deftest part-1
  (testing "1"
    (is (= (day-9/part-1 "ADVENT") 6)))
  (testing "2"
    (is (= (day-9/part-1 "A(1x5)BC") 7)))
  (testing "3"
    (is (= (day-9/part-1 "(3x3)XYZ") 9)))
  (testing "4"
    (is (= (day-9/part-1 "A(2x2)BCD(2x2)EFG") 11)))
  (testing "5"
    (is (= (day-9/part-1 "(6x1)(1x3)A") 6)))
  (testing "6"
    (is (= (day-9/part-1 "X(8x2)(3x3)ABCY") 18))))

(deftest part-2
  (testing "1"
    (is (= (day-9/part-2 "(3x3)XYZ") 9)))
  (testing "2"
    (is (= (day-9/part-2 "X(8x2)(3x3)ABCY") 20)))
  (testing "3"
    (is (= (day-9/part-2 "(27x12)(20x12)(13x14)(7x10)(1x12)A") 241920)))
  (testing "4"
    (is (= (day-9/part-2 "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN") 445))))
