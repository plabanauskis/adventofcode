(ns core-test.y2015.day-4-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-4 :as day-4]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= (day-4/part-1 (first (read-resource "2015-test/4/1.txt"))) 609043)))
  (testing "2"
    (is (= (day-4/part-1 (first (read-resource "2015-test/4/2.txt"))) 1048970))))
