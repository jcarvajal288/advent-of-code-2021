(ns advent-of-code-2021.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2021.day1 :refer :all]
            [advent-of-code-2021.util :refer :all]))

(deftest day1-sample-test
  (testing "number of measurements is incorrect"
    (let [measurements (get-resources-by-line "day1-example-data.txt")]
    (is (= (numMeasurementIncreases measurements) 7)))))
