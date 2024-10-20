(ns core-test.y2016.day-17-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-17 :as day-17]))

(deftest part-1
  (testing "1"
    (is (= "DDRRRD" (day-17/part-1 "ihgpwlah"))))
  (testing "2"
    (is (= "DDUDRLRRUDRD" (day-17/part-1 "kglvqrro"))))
  (testing "3"
    (is (= "DRURDRUDDLLDLUURRDULRLDUUDDDRR" (day-17/part-1 "ulqzkmiv")))))

(deftest part-2
  (testing "1"
    (is (= 370 (day-17/part-2 "ihgpwlah"))))
  (testing "2"
    (is (= 492 (day-17/part-2 "kglvqrro"))))
  (testing "3"
    (is (= 830 (day-17/part-2 "ulqzkmiv")))))
