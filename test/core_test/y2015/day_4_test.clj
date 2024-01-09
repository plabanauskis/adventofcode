(ns core-test.y2015.day-4-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-4 :as day-4]))

(deftest part-1
  (testing "1"
    (is (= (day-4/part-1 "abcdef") 609043)))
  (testing "2"
    (is (= (day-4/part-1 "pqrstuv") 1048970))))
