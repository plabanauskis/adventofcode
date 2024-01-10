(ns core-test.y2016.day-10-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-10 :as day-10]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (with-bindings {#'day-10/microchips #{2 5}}
      (is (= (day-10/part-1 (read-resource "2016-test/10.txt")) 2)))))
