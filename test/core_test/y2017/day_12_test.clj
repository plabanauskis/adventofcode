(ns core-test.y2017.day-12-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-12 :as day-12]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 6 (day-12/part-1 (read-resource "2017-test/12.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 2 (day-12/part-2 (read-resource "2017-test/12.txt"))))))
