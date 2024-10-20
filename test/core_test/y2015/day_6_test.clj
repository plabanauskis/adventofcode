(ns core-test.y2015.day-6-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-6 :as day-6]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "6"
    (is (= 998996 (day-6/part-1 (read-resource "2015-test/6/1.txt"))))))

(deftest part-2
  (testing "6"
    (is (= 2000001 (day-6/part-2 (read-resource "2015-test/6/2.txt"))))))
