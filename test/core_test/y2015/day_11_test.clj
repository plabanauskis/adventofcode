(ns core-test.y2015.day-11-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-11 :as day-11]))

(deftest part-1
  (testing "11"
    (is (= (day-11/part-1 "abcdefgh") "abcdffaa"))
    (is (= (day-11/part-1 "ghijklmn") "ghjaabcc"))))
