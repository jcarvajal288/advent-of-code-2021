(ns advent-of-code-2021.day1
  (:require [advent-of-code-2021.util :refer :all]))

(defn parse-int [s]
  (Integer/parseInt (re-find #"\d+" s)))

(defn determine-increase [measurement measurement-map]
  (let [index (get measurement 0)
        depth (get measurement 1)]
    (if (= index 0)
      false
      (> (parse-int depth) (parse-int (get (get measurement-map (- index 1)) 1)))))
  )

(defn find-increases [measurement-map]
  (reduce (fn [increases-list measurement]
            (into increases-list (list (determine-increase measurement measurement-map))))
          []
          measurement-map)
  )

(defn numMeasurementIncreases [file]
  (let [measurements (vec (get-resource-file-by-line file))
        increase-list (find-increases (vec (map-indexed vector measurements)))]
    (reduce + (map (fn [increase] (if increase 1 0)) increase-list))
    )
  )
