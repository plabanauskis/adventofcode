(ns core-test.y2017.day-10-test(with-bindings {#'day-10/num-elements 5}
      (is (= 12 (day-10/part-1 "3,4,1,5"))))
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-10 :as day-10]))

(deftest part-1
  (testing "1"
    (with-bindings {#'day-10/num-elements 5}
      (is (= 12 (day-10/part-1 "3,4,1,5"))))))

(deftest part-2
  (testing "2"
    (is (= "a2582a3a0e66e6e86e3812dcb672a272" (day-10/part-2 "")))
    (is (= "33efeb34ea91902bb2f59c9920caa6cd" (day-10/part-2 "AoC 2017")))
    (is (= "3efbe78a8d82f29979031a4aa0b16a9d" (day-10/part-2 "1,2,3")))
    (is (= "63960835bcdc130f0b66d7ff4f6a5a8e" (day-10/part-2 "1,2,4")))))
