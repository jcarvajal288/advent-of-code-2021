(ns advent-of-code-2021.day2-test
  (:require [advent-of-code-2021.day2 :refer :all]
            [advent-of-code-2021.util :refer :all]
            [clojure.test :refer :all]))

(deftest read-input-into-map
  (testing "read input into map"
    (let [raw-instructions (vec (get-resource-file-by-line "day2-example-data.txt"))
          instructions (parse-instructions raw-instructions)
          first-instruction (first instructions)]
      (is (coll? instructions))
      (is (coll? first-instruction))
      (is (= first-instruction '(:forward 5)))
      )
    )
  )

(deftest day2-sample-test
  (testing "day2-sample-test"
    (is (= (find-position-product "day2-example-data.txt") 150))))

(deftest day2-full-test
  (testing "day2-full-test"
    (is (= (find-position-product "day2-full-data.txt") 1427868))))

