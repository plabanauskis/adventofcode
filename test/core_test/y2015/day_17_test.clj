(ns core-test.y2015.day-17-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-17 :as day-17]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "17"
    (with-bindings {#'day-17/volume 25}
      (is (= 4 (day-17/part-1 (read-resource "2015-test/17.txt")))))))

(deftest part-2
  (testing "17"
    (with-bindings {#'day-17/volume 25}
      (is (= 3 (day-17/part-2 (read-resource "2015-test/17.txt")))))))