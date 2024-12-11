(ns core-test.y2024.day-3-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-3 :as day-3]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 161 (day-3/part-1 (read-resource "2024-test/3/3.1.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 48 (day-3/part-2 (read-resource "2024-test/3/3.2.txt"))))))
