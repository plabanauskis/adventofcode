(ns core-test.y2017.day-13-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-13 :as day-13]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 24 (day-13/part-1 (read-resource "2017-test/13.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 10 (day-13/part-2 (read-resource "2017-test/13.txt"))))))
