(ns core-test.y2015.day-11-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-11 :as day-11]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "11"
    (is (= (day-11/part-1 (first (read-resource "2015-test/11/1.txt"))) "abcdffaa"))
    (is (= (day-11/part-1 (first (read-resource "2015-test/11/2.txt"))) "ghjaabcc"))))
