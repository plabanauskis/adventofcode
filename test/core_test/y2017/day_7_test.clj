(ns core-test.y2017.day-7-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-7 :as day-7]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= "tknk" (day-7/part-1 (read-resource "2017-test/7.txt"))))))

(deftest part-2
  (testing "2"
    (is (= 60 (day-7/part-2 (read-resource "2017-test/7.txt"))))))
