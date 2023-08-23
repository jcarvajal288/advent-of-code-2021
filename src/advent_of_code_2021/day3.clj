(ns advent-of-code-2021.day3
  (:require [advent-of-code-2021.util :refer :all]
            [clojure.math.numeric-tower :as math :refer [expt]]))

(defn invert-lines [lines]
  (apply map list lines)
  )

(defn bitlist-to-decimal [bitlist]
  (let [indexed-list (map-indexed vector (reverse bitlist))
        raise (fn [pair]
                (let [power (first pair)
                      digit (last pair)]
                  (* digit (expt 2 power))))
        ]
    (reduce + (map raise indexed-list))
    )
  )

(defn find-power-consumption [filename]
  (let [raw-data (vec (get-resource-file-by-line filename))
        transposed-data (invert-lines raw-data)
        counted-and-sorted-bits (map #(reverse (sort-by val %)) (map frequencies transposed-data))
        most-common-bits (map #(Character/digit ^Character % 10) (map #(first (first %)) counted-and-sorted-bits))
        least-common-bits (map (fn [bit] (if (= bit 0) 1 0)) most-common-bits)
        ]
    (* (bitlist-to-decimal most-common-bits) (bitlist-to-decimal least-common-bits))))

(defn- keep-frequent-ones [remaining-numbers position]
  (let [ones (filter #(= (nth % position) \1) remaining-numbers)]
    (if (>= (count ones) (/ (count remaining-numbers) 2))
      ones
      (filter #(= (nth % position) \0) remaining-numbers))))

(defn- keep-frequent-zeroes [remaining-numbers position]
  (let [zeroes (filter #(= (nth % position) \0) remaining-numbers)]
    (if (<= (count zeroes) (/ (count remaining-numbers) 2))
      zeroes
      (filter #(= (nth % position) \1) remaining-numbers))))

(defn- binary-to-decimal [bin]
  (let [reducer (fn [total next]
                  (if (empty? next) total
                    (if (= (first next) \1)
                      (recur (+ total (math/expt 2 (dec (count next)))) (rest next))
                      (recur total (rest next)))))]
    (reducer 0 bin)))

(defn find-oxygen-generator-rating [raw-data]
  (let [reducer (fn [remaining-numbers positions]
                  (if (<= (count remaining-numbers) 1)
                    (binary-to-decimal (first remaining-numbers))
                    (recur (keep-frequent-ones remaining-numbers (first positions)) (rest positions))))]
    (reducer raw-data (range (count (nth raw-data 0))))))

(defn find-C02-scrubber-rating [raw-data]
  (let [reducer (fn [remaining-numbers positions]
                  (if (<= (count remaining-numbers) 1)
                    (binary-to-decimal (first remaining-numbers))
                    (recur (keep-frequent-zeroes remaining-numbers (first positions)) (rest positions))))]
    (reducer raw-data (range (count (nth raw-data 0))))))

(defn find-life-support-rating [filename]
  (let [raw-data (vec (get-resource-file-by-line filename))]
    (* (find-oxygen-generator-rating raw-data) (find-C02-scrubber-rating raw-data))))
