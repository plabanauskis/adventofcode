(ns core-test.y2015.day-10-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-10 :as day-10]))

(deftest part-1
  (testing "10"
    (is (= 82350 (day-10/part-1 "1")))))

(deftest part-2
  (testing "10"
    (is (= 1166642 (day-10/part-2 "1")))))
