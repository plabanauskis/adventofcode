(ns core-test.y2024.day-13-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-13 :as day-13]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 480 (day-13/part-1 (read-resource "2024-test/13.txt"))))))
