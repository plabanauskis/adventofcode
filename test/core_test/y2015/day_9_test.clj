(ns core-test.y2015.day-9-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-9 :as day-9]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "9"
    (is (= 605 (day-9/part-1 (read-resource "2015-test/9.txt"))))))

(deftest part-2
  (testing "9"
    (is (= 982 (day-9/part-2 (read-resource "2015-test/9.txt"))))))
