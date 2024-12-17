(ns core-test.y2024.day-14-test
  (:require [clojure.test :refer :all]
            [aoc.y2024.day-14 :as day-14]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (with-bindings {#'day-14/width 11
                    #'day-14/height 7}
      (is (= 12 (day-14/part-1 (read-resource "2024-test/14.txt")))))))
