(ns core-test.y2016.day-21-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-21 :as day-21]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (with-bindings {#'day-21/password-pt1 "abcde"}
      (is (= (day-21/part-1 (read-resource "2016-test/21.txt")) "decab")))))
