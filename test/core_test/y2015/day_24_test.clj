(ns core-test.y2015.day-24-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-24 :as day-24]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "24"
    (is (= (day-24/part-1 (read-resource "2015-test/24.txt")) 99))))

(deftest part-2
  (testing "24"
    (is (= (day-24/part-2 (read-resource "2015-test/24.txt")) 44))))