(ns core-test.y2016.day-14-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-14 :as day-14]))

(deftest part-1
  (testing "1"
    (is (= 22728 (day-14/part-1 "abc")))))

(deftest part-2
  (testing "1"
    (is (= 22551 (day-14/part-2 "abc")))))
