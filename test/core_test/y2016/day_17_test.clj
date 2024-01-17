(ns core-test.y2016.day-17-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-17 :as day-17]))

(deftest part-1
  (testing "1"
    (is (= (day-17/part-1 "ihgpwlah") "DDRRRD")))
  (testing "2"
    (is (= (day-17/part-1 "kglvqrro") "DDUDRLRRUDRD")))
  (testing "3"
    (is (= (day-17/part-1 "ulqzkmiv") "DRURDRUDDLLDLUURRDULRLDUUDDDRR"))))

(deftest part-2
  (testing "1"
    (is (= (day-17/part-2 "ihgpwlah") 370)))
  (testing "2"
    (is (= (day-17/part-2 "kglvqrro") 492)))
  (testing "3"
    (is (= (day-17/part-2 "ulqzkmiv") 830))))
