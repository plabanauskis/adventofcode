(ns core-test.y2017.day-14-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-14 :as day-14]))

(deftest part-1
  (testing "1"
    (is (= 8108 (day-14/part-1 "flqrgnkx")))))

(deftest part-2
  (testing "2"
    (is (= 1242 (day-14/part-2 "flqrgnkx")))))
