(ns core-test.y2015.day-4-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-4 :as day-4]))

(deftest part-1
  (testing "1"
    (is (= 609043 (day-4/part-1 "abcdef"))))
  (testing "2"
    (is (= 1048970 (day-4/part-1 "pqrstuv")))))
