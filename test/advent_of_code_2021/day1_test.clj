(ns advent-of-code-2021.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2021.day1 :refer :all]
            [advent-of-code-2021.util :refer :all]))

(deftest day1-sample-test
  (testing "day1-sample-test"
    (is (= (numMeasurementIncreases "day1-example-data.txt") 7))))

(deftest day1-full-test
  (testing "day1-full-test"
    (is (= (numMeasurementIncreases "day1-full-data.txt") 1676))))

(deftest test-expand-measurement-windows
  (testing "expand measurement list into 3 measurement windows"
    (let [measurements (get-resource-file-by-line "day1-example-data.txt")
          measurement-windows (find-measurement-windows measurements)]
      (is (= (count measurement-windows) 8))
      (is (coll? (get (vec measurement-windows) 0)))
      )
    )
  )

