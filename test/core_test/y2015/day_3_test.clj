(ns core-test.y2015.day-3-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-3 :as day-3]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= (day-3/part-1 (first (read-resource "2015-test/3/1.txt"))) 2)))
  (testing "2"
    (is (= (day-3/part-1 (first (read-resource "2015-test/3/2.txt"))) 4)))
  (testing "3"
    (is (= (day-3/part-1 (first (read-resource "2015-test/3/3.txt"))) 2))))

(deftest part-2
  (testing "2"
    (is (= (day-3/part-2 (first (read-resource "2015-test/3/2.txt"))) 3)))
  (testing "3"
    (is (= (day-3/part-2 (first (read-resource "2015-test/3/3.txt"))) 11)))
  (testing "4"
    (is (= (day-3/part-2 (first (read-resource "2015-test/3/4.txt"))) 3))))
