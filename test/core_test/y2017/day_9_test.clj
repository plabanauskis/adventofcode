(ns core-test.y2017.day-9-test
  (:require [clojure.test :refer :all]
            [aoc.y2017.day-9 :as day-9]))

(deftest part-1
  (testing "1"
    (is (= 1 (day-9/part-1 "{}")))
    (is (= 6 (day-9/part-1 "{{{}}}")))
    (is (= 5 (day-9/part-1 "{{},{}}")))
    (is (= 16 (day-9/part-1 "{{{},{},{{}}}}")))
    (is (= 1 (day-9/part-1 "{<a>,<a>,<a>,<a>}")))
    (is (= 9 (day-9/part-1 "{{<ab>},{<ab>},{<ab>},{<ab>}}")))
    (is (= 9 (day-9/part-1 "{{<!!>},{<!!>},{<!!>},{<!!>}}")))
    (is (= 3 (day-9/part-1 "{{<a!>},{<a!>},{<a!>},{<ab>}}")))))

(deftest part-2
  (testing "2"
    (is (= 0 (day-9/part-2 "<>")))
    (is (= 17 (day-9/part-2 "<random characters>")))
    (is (= 3 (day-9/part-2 "<<<<>")))
    (is (= 2 (day-9/part-2 "<{!>}>")))
    (is (= 0 (day-9/part-2 "<!!>")))
    (is (= 0 (day-9/part-2 "<!!!>>")))
    (is (= 10 (day-9/part-2 "<{o\"i!a,<{i<a>")))))
