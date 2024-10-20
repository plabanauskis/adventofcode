(ns core-test.y2016.day-24-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-24 :as day-24]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 14 (day-24/part-1 (read-resource "2016-test/24.txt"))))))
