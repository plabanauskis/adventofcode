(ns core-test.y2024.day-10-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-10 :as day-10]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 36 (day-10/part-1 (read-resource "2024-test/10.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 81 (day-10/part-2 (read-resource "2024-test/10.txt"))))))
