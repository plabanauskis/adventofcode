(ns core-test.y2016.day-23-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-23 :as day-23]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= (day-23/part-1 (read-resource "2016-test/23.txt")) 3))))
