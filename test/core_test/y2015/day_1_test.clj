(ns core-test.y2015.day-1-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-1 :as day-1]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= (day-1/part-1 (first (read-resource "2015-test/1/1.txt"))) 0)))
  (testing "2"
    (is (= (day-1/part-1 (first (read-resource "2015-test/1/2.txt"))) 0)))
  (testing "3"
    (is (= (day-1/part-1 (first (read-resource "2015-test/1/3.txt"))) 3)))
  (testing "4"
    (is (= (day-1/part-1 (first (read-resource "2015-test/1/4.txt"))) 3)))
  (testing "5"
    (is (= (day-1/part-1 (first (read-resource "2015-test/1/5.txt"))) 3)))
  (testing "6"
    (is (= (day-1/part-1 (first (read-resource "2015-test/1/6.txt"))) -1)))
  (testing "7"
    (is (= (day-1/part-1 (first (read-resource "2015-test/1/7.txt"))) -1)))
  (testing "8"
    (is (= (day-1/part-1 (first (read-resource "2015-test/1/8.txt"))) -3)))
  (testing "9"
    (is (= (day-1/part-1 (first (read-resource "2015-test/1/9.txt"))) -3))))

(deftest part-2
  (testing "10"
    (is (= (day-1/part-2 (first (read-resource "2015-test/1/10.txt"))) 1)))
  (testing "11"
    (is (= (day-1/part-2 (first (read-resource "2015-test/1/11.txt"))) 5))))
