(ns core-test.y2024.day-9-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-9 :as day-9]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 1928 (day-9/part-1 (first (read-resource "2024-test/9.txt")))))))

(deftest part-2
  (testing "2"
    (is (= 2858 (day-9/part-2 (first (read-resource "2024-test/9.txt")))))))
