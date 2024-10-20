(ns core-test.y2015.day-3-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-3 :as day-3]))

(deftest part-1
  (testing "1"
    (is (= 2 (day-3/part-1 ">"))))
  (testing "2"
    (is (= 4 (day-3/part-1 "^>v<"))))
  (testing "3"
    (is (= 2 (day-3/part-1 "^v^v^v^v^v")))))

(deftest part-2
  (testing "2"
    (is (= 3 (day-3/part-2 "^>v<"))))
  (testing "3"
    (is (= 11 (day-3/part-2 "^v^v^v^v^v"))))
  (testing "4"
    (is (= 3 (day-3/part-2 "^v")))))
