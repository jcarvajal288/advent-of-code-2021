(ns advent-of-code-2021.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2021.day1 :refer :all]
            [clojure.string :as str]))

(deftest day1-sample-test
  (testing "number of measurements is incorrect"
    (let [content (slurp (.getPath (clojure.java.io/resource "day1-example-data.txt")))
          measurements (str/split-lines content)]
    (is (= (numMeasurementIncreases measurements) 7)))))
