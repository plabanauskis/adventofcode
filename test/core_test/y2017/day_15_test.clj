(ns core-test.y2017.day-15-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-15 :as day-15]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 588 (day-15/part-1 (read-resource "2017-test/15.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 309 (day-15/part-2 (read-resource "2017-test/15.txt"))))))
