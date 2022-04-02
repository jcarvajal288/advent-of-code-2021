(ns advent-of-code-2021.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2021.day1 :refer :all]
            [advent-of-code-2021.util :refer :all]))

(deftest day1-sample-test
  (testing "day1-sample-test is incorrect"
    (let [measurements (numMeasurementIncreases "day1-example-data.txt")]
      (is (= (count measurements) 10))
      (is (= (get measurements 0) false))
      )))
