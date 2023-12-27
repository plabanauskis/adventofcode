(ns core-test.y2015.day-12-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-12 :as day-12]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "12"
    (is (= (day-12/part-1 (first (read-resource "2015-test/12/1.txt"))) 6))
    (is (= (day-12/part-1 (first (read-resource "2015-test/12/2.txt"))) 6))
    (is (= (day-12/part-1 (first (read-resource "2015-test/12/3.txt"))) 3))
    (is (= (day-12/part-1 (first (read-resource "2015-test/12/4.txt"))) 3))
    (is (= (day-12/part-1 (first (read-resource "2015-test/12/5.txt"))) 0))
    (is (= (day-12/part-1 (first (read-resource "2015-test/12/6.txt"))) 0))
    (is (= (day-12/part-1 (first (read-resource "2015-test/12/7.txt"))) 0))
    (is (= (day-12/part-1 (first (read-resource "2015-test/12/8.txt"))) 0))))

(deftest part-2
  (testing "12"
    (is (= (day-12/part-2 (first (read-resource "2015-test/12/1.txt"))) 6))
    (is (= (day-12/part-2 (first (read-resource "2015-test/12/9.txt"))) 4))
    (is (= (day-12/part-2 (first (read-resource "2015-test/12/10.txt"))) 0))
    (is (= (day-12/part-2 (first (read-resource "2015-test/12/11.txt"))) 6))))
