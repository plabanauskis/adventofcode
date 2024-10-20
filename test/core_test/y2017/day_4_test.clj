(ns core-test.y2017.day-4-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-4 :as day-4]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 2 (day-4/part-1 (read-resource "2017-test/4/1.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 3 (day-4/part-2 (read-resource "2017-test/4/2.txt"))))))
