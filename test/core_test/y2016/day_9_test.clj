(ns core-test.y2016.day-9-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-9 :as day-9]))

(deftest part-1
  (testing "1"
    (is (= 6 (day-9/part-1 "ADVENT"))))
  (testing "2"
    (is (= 7 (day-9/part-1 "A(1x5)BC"))))
  (testing "3"
    (is (= 9 (day-9/part-1 "(3x3)XYZ"))))
  (testing "4"
    (is (= 11 (day-9/part-1 "A(2x2)BCD(2x2)EFG"))))
  (testing "5"
    (is (= 6 (day-9/part-1 "(6x1)(1x3)A"))))
  (testing "6"
    (is (= 18 (day-9/part-1 "X(8x2)(3x3)ABCY")))))

(deftest part-2
  (testing "1"
    (is (= 9 (day-9/part-2 "(3x3)XYZ"))))
  (testing "2"
    (is (= 20 (day-9/part-2 "X(8x2)(3x3)ABCY"))))
  (testing "3"
    (is (= 241920 (day-9/part-2 "(27x12)(20x12)(13x14)(7x10)(1x12)A"))))
  (testing "4"
    (is (= 445 (day-9/part-2 "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN")))))
