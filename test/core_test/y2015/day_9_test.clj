(ns core-test.y2015.day-9-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-9 :as day-9]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "9"
    (is (= (day-9/part-1 (read-resource "2015-test/9.txt")) 605))))

(deftest part-2
  (testing "9"
    (is (= (day-9/part-2 (read-resource "2015-test/9.txt")) 982))))
