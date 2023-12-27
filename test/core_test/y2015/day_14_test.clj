(ns core-test.y2015.day-14-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-14 :as day-14]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "14"
    (with-bindings {#'day-14/travel-time 1000}
      (is (= (day-14/part-1 (read-resource "2015-test/14.txt")) 1120)))))

(deftest part-2
  (testing "14"
    (with-bindings {#'day-14/travel-time 1000}
      (is (= (day-14/part-2 (read-resource "2015-test/14.txt")) 689)))))