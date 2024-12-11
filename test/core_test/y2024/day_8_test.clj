(ns core-test.y2024.day-8-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-8 :as day-8]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 14 (day-8/part-1 (read-resource "2024-test/8.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 34 (day-8/part-2 (read-resource "2024-test/8.txt"))))))
