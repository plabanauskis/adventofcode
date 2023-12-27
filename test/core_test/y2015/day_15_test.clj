(ns core-test.y2015.day-15-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-15 :as day-15]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "15"
    (is (= (day-15/part-1 (read-resource "2015-test/15.txt")) 62842880))))

(deftest part-2
  (testing "15"
    (is (= (day-15/part-2 (read-resource "2015-test/15.txt")) 57600000))))
