(ns core-test.y2016.day-2-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-2 :as day-2]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= "1985" (day-2/part-1 (read-resource "2016-test/2.txt"))))))

(deftest part-2
  (testing "1"
    (is (= "5DB3" (day-2/part-2 (read-resource "2016-test/2.txt"))))))
