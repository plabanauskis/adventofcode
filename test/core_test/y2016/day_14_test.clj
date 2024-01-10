(ns core-test.y2016.day-14-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-14 :as day-14]))

(deftest part-1
  (testing "1"
    (is (= (day-14/part-1 "abc") 22728))))

(deftest part-2
  (testing "1"
    (is (= (day-14/part-2 "abc") 22551))))
