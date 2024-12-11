(ns core-test.y2024.day-4-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-4 :as day-4]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 18 (day-4/part-1 (read-resource "2024-test/4.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 9 (day-4/part-2 (read-resource "2024-test/4.txt"))))))
