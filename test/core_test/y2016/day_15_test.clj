(ns core-test.y2016.day-15-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-15 :as day-15]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= (day-15/part-1 (read-resource "2016-test/15.txt")) 5))))
