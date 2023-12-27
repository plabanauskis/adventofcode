(ns core-test.y2015.day-10-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-10 :as day-10]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "10"
    (is (= (day-10/part-1 (read-resource "2015-test/10.txt")) 82350))))

(deftest part-2
  (testing "10"
    (is (= (day-10/part-2 (read-resource "2015-test/10.txt")) 1166642))))
