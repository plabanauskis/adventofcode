(ns core-test.y2017.day-8-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-8 :as day-8]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 1 (day-8/part-1 (read-resource "2017-test/8.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 10 (day-8/part-2 (read-resource "2017-test/8.txt"))))))
