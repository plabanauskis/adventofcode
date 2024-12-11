(ns core-test.y2024.day-5-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-5 :as day-5]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 143 (day-5/part-1 (read-resource "2024-test/5.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 123 (day-5/part-2 (read-resource "2024-test/5.txt"))))))
