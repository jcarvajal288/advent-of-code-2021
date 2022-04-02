(ns advent-of-code-2021.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2021.day1 :refer :all]
            [advent-of-code-2021.util :refer :all]))

(deftest day1-sample-test
  (testing "day1-sample-test is incorrect"
    (is (= (numMeasurementIncreases "day1-example-data.txt") 7))))

(deftest day1-full-test
  (testing "day1-full-test is incorrect"
    (is (= (numMeasurementIncreases "day1-full-data.txt") 1676))))
