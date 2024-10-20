(ns core-test.y2015.day-8-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-8 :as day-8]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "8"
    (is (= 12 (day-8/part-1 (read-resource "2015-test/8.txt"))))))

(deftest part-2
  (testing "8"
    (is (= 19 (day-8/part-2 (read-resource "2015-test/8.txt"))))))
