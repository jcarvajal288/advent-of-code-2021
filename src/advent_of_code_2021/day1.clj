(ns advent-of-code-2021.day1
  (:require [advent-of-code-2021.util :refer :all]))

(defn parse-int [s]
  (Integer/parseInt (re-find #"\d+" s)))

(defn count-increases [increases]
  (reduce + (map (fn [increase] (if increase 1 0)) increases)))

(defn determine-increase [measurement measurement-map]
  (let [index (get measurement 0)
        depth (get measurement 1)]
    (if (= index 0)
      false
      (> depth (get (get measurement-map (- index 1)) 1))))
  )

(defn find-increases [measurement-map]
  (reduce (fn [increases-list measurement]
            (into increases-list (list (determine-increase measurement measurement-map))))
          []
          measurement-map)
  )

(defn numMeasurementIncreases [file]
  (let [raw-measurements (vec (get-resource-file-by-line file))
        measurements (map parse-int raw-measurements)
        increase-list (find-increases (vec (map-indexed vector measurements)))]
    (count-increases increase-list)
    )
  )

(defn expand-measurement-windows [measurements result]
  (if (>= (count measurements) 3)
    (let [new-result (conj result (map parse-int (take 3 measurements)))]
      (expand-measurement-windows (rest measurements) new-result)
      )
    result
    )
  )

(defn find-measurement-windows [measurements]
  (expand-measurement-windows measurements []))

(defn num-increases-in-windows [file]
  (let [measurements (vec (get-resource-file-by-line file))
        measurement-windows (find-measurement-windows measurements)
        summed-windows (map #(reduce + %) measurement-windows)
        increase-list (find-increases (vec (map-indexed vector summed-windows)))]
    (count-increases increase-list)
    )
  )



