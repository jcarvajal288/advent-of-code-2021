(ns advent-of-code-2021.day3
  (:require [advent-of-code-2021.util :refer :all]
            [clojure.math.numeric-tower :as math :refer [expt]]
            ))

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
    (* (bitlist-to-decimal most-common-bits) (bitlist-to-decimal least-common-bits))
    )
  )
