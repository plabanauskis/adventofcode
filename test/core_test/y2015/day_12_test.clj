(ns core-test.y2015.day-12-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-12 :as day-12]))

(deftest part-1
  (testing "12"
    (is (= 6 (day-12/part-1 "[1,2,3]")))
    (is (= 6 (day-12/part-1 "{\"a\":2,\"b\":4}")))
    (is (= 3 (day-12/part-1 "[[[3]]]")))
    (is (= 3 (day-12/part-1 "{\"a\":{\"b\":4},\"c\":-1}")))
    (is (= 0 (day-12/part-1 "{\"a\":[-1,1]}")))
    (is (= 0 (day-12/part-1 "[-1,{\"a\":1}]")))
    (is (= 0 (day-12/part-1 "[]")))
    (is (= 0 (day-12/part-1 "{}")))))

(deftest part-2
  (testing "12"
    (is (= 6 (day-12/part-2 "[1,2,3]")))
    (is (= 4 (day-12/part-2 "[1,{\"c\":\"red\",\"b\":2},3]")))
    (is (= 0 (day-12/part-2 "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}")))
    (is (= 6 (day-12/part-2 "[1,\"red\",5]")))))
