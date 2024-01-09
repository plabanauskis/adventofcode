(ns core-test.y2015.day-12-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-12 :as day-12]))

(deftest part-1
  (testing "12"
    (is (= (day-12/part-1 "[1,2,3]") 6))
    (is (= (day-12/part-1 "{\"a\":2,\"b\":4}") 6))
    (is (= (day-12/part-1 "[[[3]]]") 3))
    (is (= (day-12/part-1 "{\"a\":{\"b\":4},\"c\":-1}") 3))
    (is (= (day-12/part-1 "{\"a\":[-1,1]}") 0))
    (is (= (day-12/part-1 "[-1,{\"a\":1}]") 0))
    (is (= (day-12/part-1 "[]") 0))
    (is (= (day-12/part-1 "{}") 0))))

(deftest part-2
  (testing "12"
    (is (= (day-12/part-2 "[1,2,3]") 6))
    (is (= (day-12/part-2 "[1,{\"c\":\"red\",\"b\":2},3]") 4))
    (is (= (day-12/part-2 "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}") 0))
    (is (= (day-12/part-2 "[1,\"red\",5]") 6))))
