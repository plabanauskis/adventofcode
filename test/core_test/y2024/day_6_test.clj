(ns core-test.y2024.day-6-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-6 :as day-6]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 41 (day-6/part-1 (read-resource "2024-test/6.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 6 (day-6/part-2 (read-resource "2024-test/6.txt"))))))
