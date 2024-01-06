(ns core-test.y2015.day-22-test
  (:require [clojure.test :refer :all]
            [aoc.y2015.day-22 :as day-22]
            [utils :refer [read-resource]]))

(deftest part-1
  (with-bindings {#'day-22/player {:hp 10 :mana 250}}
    (testing "22.1"
      (is (= (day-22/part-1 (read-resource "2015-test/22/1.txt")) 226)))
    (testing "22.2"
      (is (= (day-22/part-1 (read-resource "2015-test/22/2.txt")) 641)))))