(ns advent-of-code-2021.day3-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2021.util :refer :all]
            [advent-of-code-2021.day3 :refer :all]
            ))

(deftest lines-inversion
  (testing "inverting a list of lists"
    (let [raw-data (vec (get-resource-file-by-line "day3-example-data.txt"))
          inverted-data (invert-lines raw-data)]
      (println inverted-data)
      (is (= (count inverted-data)) 5)
      (is (= (count (first inverted-data)) 12)))))

(deftest test-binlist-to-decimal
  (testing "transform list of binary digits to a decimal number"
    (is (= (bitlist-to-decimal '(1 0 1 1 0)) 22))))

(deftest day3-part1-sample-test
  (testing "day3 part 1 with sample data"
    (is (= (find-power-consumption "day3-example-data.txt") 198))))

(deftest day3-part1-full-test
  (testing "day3 part 1 with full data"
    (is (= (find-power-consumption "day3-full-data.txt") 841526))))

