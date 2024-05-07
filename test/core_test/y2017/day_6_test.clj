(ns core-test.y2017.day-6-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-6 :as day-6]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= (day-6/part-1 "0\t2\t7\t0") 5))))

(deftest part-2
  (testing "2"
    (is (= (day-6/part-2 "0\t2\t7\t0") 4))))
