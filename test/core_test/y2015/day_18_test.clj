(ns core-test.y2015.day-18-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-18 :as day-18]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "18"
    (with-bindings {#'day-18/steps 4}
      (is (= (day-18/part-1 (read-resource "2015-test/18/1.txt")) 4)))))

(deftest part-2
  (testing "18"
    (with-bindings {#'day-18/steps 5}
      (is (= (day-18/part-2 (read-resource "2015-test/18/2.txt")) 17)))))