(ns core-test.y2016.day-4-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-4 :as day-4]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 1514 (day-4/part-1 (read-resource "2016-test/4.txt"))))))
