(ns core-test.y2017.day-16-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-16 :as day-16]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (with-bindings {#'day-16/programs "abcde"}
      (is (= "baedc" (day-16/part-1 (first (read-resource "2017-test/16.txt"))))))))
