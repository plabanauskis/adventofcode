(ns core-test.y2024.day-12-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-12 :as day-12]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 140 (day-12/part-1 (read-resource "2024-test/12/12.1.txt"))))
    (is (= 772 (day-12/part-1 (read-resource "2024-test/12/12.2.txt"))))
    (is (= 1930 (day-12/part-1 (read-resource "2024-test/12/12.3.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 80 (day-12/part-2 (read-resource "2024-test/12/12.1.txt"))))
    (is (= 436 (day-12/part-2 (read-resource "2024-test/12/12.2.txt"))))
    (is (= 1206 (day-12/part-2 (read-resource "2024-test/12/12.3.txt"))))
    (is (= 236 (day-12/part-2 (read-resource "2024-test/12/12.4.txt"))))
    (is (= 368 (day-12/part-2 (read-resource "2024-test/12/12.5.txt"))))))
