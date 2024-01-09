(ns core-test.y2016.day-8-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-8 :as day-8]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (with-bindings {#'day-8/screen-size [7 3]}
      (is (= (day-8/part-1 (read-resource "2016-test/8.txt")) 6)))))
