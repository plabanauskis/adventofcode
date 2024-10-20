(ns core-test.y2016.day-5-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-5 :as day-5]))

(deftest part-1
  (testing "1"
    (is (= "18f47a30" (day-5/part-1 "abc")))))

(deftest part-2
  (testing "1"
    (is (= "05ace8e3" (day-5/part-2 "abc")))))
