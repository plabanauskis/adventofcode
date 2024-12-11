(ns core-test.y2024.day-2-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-2 :as day-2]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 2 (day-2/part-1 (read-resource "2024-test/2.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 4 (day-2/part-2 (read-resource "2024-test/2.txt"))))))
