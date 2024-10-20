(ns core-test.y2017.day-3-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-3 :as day-3]))

(deftest part-1
  (testing "1"
    (is (= 0 (day-3/part-1 "1")))
    (is (= 3 (day-3/part-1 "12")))
    (is (= 2 (day-3/part-1 "23")))
    (is (= 31 (day-3/part-1 "1024")))))
