(ns core-test.y2024.day-1-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-1 :as day-1]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 11 (day-1/part-1 (read-resource "2024-test/1.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 31 (day-1/part-2 (read-resource "2024-test/1.txt"))))))
