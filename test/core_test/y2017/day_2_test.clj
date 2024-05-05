(ns core-test.y2017.day-2-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-2 :as day-2]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= (day-2/part-1 (read-resource "2017-test/2/1.txt")) 18))))

(deftest part-2
  (testing "2"
    (is (= (day-2/part-2 (read-resource "2017-test/2/2.txt")) 9))))
