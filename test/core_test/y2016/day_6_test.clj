(ns core-test.y2016.day-6-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-6 :as day-6]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= (day-6/part-1 (read-resource "2016-test/6.txt")) "easter"))))

(deftest part-2
  (testing "1"
    (is (= (day-6/part-2 (read-resource "2016-test/6.txt")) "advent"))))
