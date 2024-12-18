(ns core-test.y2015.day-5-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-5 :as day-5]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 2 (day-5/part-1 (read-resource "2015-test/5/1.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 2 (day-5/part-2 (read-resource "2015-test/5/2.txt"))))))
