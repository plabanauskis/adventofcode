(ns core-test.y2015.day-19-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-19 :as day-19]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "19"
    (is (= (day-19/part-1 (read-resource "2015-test/19/1.txt")) 7))))

(deftest part-2
  (testing "19"
    (is (= (day-19/part-2 (read-resource "2015-test/19/2.txt")) 6))))