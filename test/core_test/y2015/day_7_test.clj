(ns core-test.y2015.day-7-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-7 :as day-7]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "7"
    (is (= 65079 (day-7/part-1 (read-resource "2015-test/7.txt"))))))