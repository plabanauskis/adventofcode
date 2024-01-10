(ns core-test.y2016.day-18-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-18 :as day-18]))

(deftest part-1
  (testing "1"
    (with-bindings {#'day-18/rows 10}
      (is (= (day-18/part-1 ".^^.^.^^^^") 38)))))
