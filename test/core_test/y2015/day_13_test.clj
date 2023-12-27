(ns core-test.y2015.day-13-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-13 :as day-13]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "13"
    (is (= (day-13/part-1 (read-resource "2015-test/13.txt")) 330))))

(deftest part-2
  (testing "13"
    (is (= (day-13/part-2 (read-resource "2015-test/13.txt")) 286))))
