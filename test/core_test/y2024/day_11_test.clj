(ns core-test.y2024.day-11-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-11 :as day-11]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 55312 (day-11/part-1 (first (read-resource "2024-test/11.txt")))))))
