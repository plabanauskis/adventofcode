(ns core-test.y2016.day-11-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-11 :as day-11]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= (day-11/part-1 (read-resource "2016-test/11.txt")) 11))))
