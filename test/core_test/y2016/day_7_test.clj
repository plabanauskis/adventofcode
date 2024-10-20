(ns core-test.y2016.day-7-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-7 :as day-7]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 2 (day-7/part-1 (read-resource "2016-test/7/1.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 3 (day-7/part-2 (read-resource "2016-test/7/2.txt"))))))
