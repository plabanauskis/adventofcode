(ns core-test.y2016.day-12-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-12 :as day-12]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= (day-12/part-1 (read-resource "2016-test/12.txt")) 42))))
