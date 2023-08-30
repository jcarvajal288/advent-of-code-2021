(ns advent-of-code-2021.day4-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2021.util :refer :all]
            [advent-of-code-2021.day4 :refer :all]))

(deftest day4-part1-sample-test
  (testing "day4 part 1 with sample data"
    (is (= (find-final-score "day4-example-data.txt") 4512))))

(deftest day4-part1-full-test
  (testing "day4 part 1 with full data"
    (is (= (find-final-score "day4-full-data.txt") 10680))))
