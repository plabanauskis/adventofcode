(ns core-test.y2017.day-5-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-5 :as day-5]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= (day-5/part-1 (read-resource "2017-test/5.txt")) 5))))

(deftest part-2
  (testing "2"
    (is (= (day-5/part-2 (read-resource "2017-test/5.txt")) 10))))
