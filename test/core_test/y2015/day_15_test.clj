(ns core-test.y2015.day-15-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-15 :as day-15]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "15"
    (is (= 62842880 (day-15/part-1 (read-resource "2015-test/15.txt"))))))

(deftest part-2
  (testing "15"
    (is (= 57600000 (day-15/part-2 (read-resource "2015-test/15.txt"))))))
