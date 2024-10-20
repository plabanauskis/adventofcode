(ns core-test.y2016.day-16-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-16 :as day-16]))

(deftest part-1
  (testing "1"
    (with-bindings {#'day-16/disk 20}
      (is (= "01100" (day-16/part-1 "10000"))))))
