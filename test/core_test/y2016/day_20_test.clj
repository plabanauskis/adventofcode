(ns core-test.y2016.day-20-test
  (:require [clojure.test :refer :all]
            [aoc.y2016.day-20 :as day-20]
            [utils :refer [read-resource]]))

(deftest part-1
  (testing "1"
    (is (= 3 (day-20/part-1 (read-resource "2016-test/20.txt"))))))

(deftest part-2
  (testing "1"
    (with-bindings {#'day-20/max-ip 9}
      (is (= 2 (day-20/part-2 (read-resource "2016-test/20.txt")))))))
