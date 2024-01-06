(ns core-test.y2015.day-23-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-23 :as day-23]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "23"
    (with-bindings {#'day-23/resulting-register :a}
      (is (= (day-23/part-1 (read-resource "2015-test/23.txt")) 2)))))