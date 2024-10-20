(ns core-test.y2016.day-19-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-19 :as day-19]))

(deftest part-1
  (testing "1"
    (is (= 3 (day-19/part-1 "5")))))

(deftest part-1
  (testing "1"
    (is (= 2 (day-19/part-2 "5")))))
