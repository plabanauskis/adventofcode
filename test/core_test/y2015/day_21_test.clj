(ns core-test.y2015.day-21-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-21 :as day-21]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "21"
    (is (= (day-19/part-1 (read-resource "2015-test/19/1.txt")) 7))))