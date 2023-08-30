(ns advent-of-code-2021.day4
  (:require [advent-of-code-2021.util :refer :all]
            [clojure.string :as str]))

(defn read-boards [raw-data]
  (->> raw-data
       (filter #(not (str/blank? %)))
       (str/join " ")
       (re-seq #"\d+")
       (map parse-int)
       (partition 5)
       (partition 5)))

(defn read-input-data [filename]
  (let [raw-data (get-resource-file-by-line filename)
        drawn-numbers (as-> raw-data data
                            (first data)
                            (str/split data #",")
                            (map parse-int data))
        boards (read-boards (rest raw-data))]
    [drawn-numbers boards]))

(defn winning-board? [board drawn-numbers]
  (if (some #(every? (set drawn-numbers) %) board) board nil))

(defn find-winning-state [all-drawn-numbers boards]
  (let [reducer (fn [num-drawn all-drawn-numbers boards]
                  (let [drawn-numbers (take num-drawn all-drawn-numbers)
                        winning-board (first (keep #(winning-board? % drawn-numbers) boards))]
                    (if (nil? winning-board)
                      (recur (inc num-drawn) all-drawn-numbers boards)
                      [num-drawn winning-board])))]
    (reducer 5 all-drawn-numbers boards)))

(defn calculate-final-score [numbers board]
  (->> board
       (flatten)
       (filter #(not (some #{%} numbers)))
       (reduce +)
       (* (last numbers))))

(defn find-final-score [filename]
  (let [numbers-and-boards (read-input-data filename)
        drawn-numbers (first numbers-and-boards)
        boards (last numbers-and-boards)
        transposed-boards (map #(apply mapv vector %) boards)
        winning-state (find-winning-state drawn-numbers (concat boards transposed-boards))
        final-numbers-drawn (take (first winning-state) drawn-numbers)
        winning-board (last winning-state)]
    (calculate-final-score final-numbers-drawn winning-board)))