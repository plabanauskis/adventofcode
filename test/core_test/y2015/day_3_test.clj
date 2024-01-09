(ns core-test.y2015.day-3-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-3 :as day-3]))

(deftest part-1
  (testing "1"
    (is (= (day-3/part-1 ">") 2)))
  (testing "2"
    (is (= (day-3/part-1 "^>v<") 4)))
  (testing "3"
    (is (= (day-3/part-1 "^v^v^v^v^v") 2))))

(deftest part-2
  (testing "2"
    (is (= (day-3/part-2 "^>v<") 3)))
  (testing "3"
    (is (= (day-3/part-2 "^v^v^v^v^v") 11)))
  (testing "4"
    (is (= (day-3/part-2 "^v") 3))))
