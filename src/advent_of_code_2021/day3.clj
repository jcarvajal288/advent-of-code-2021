(ns advent-of-code-2021.day3
  (:require [advent-of-code-2021.util :refer :all]
            [clojure.math.numeric-tower :as math :refer [expt]]
            ))

(defn invert-lines [lines]
  (apply map list lines)
  )

(defn binlist-to-decimal [binlist]
  (let [indexed-list (map-indexed vector (reverse binlist))
        raise (fn [pair]
                (let [power (first pair) digit (last pair)]
                  (* digit (expt 2 power))))
        ]
    (reduce + (map raise indexed-list))
    )
  )

(defn find-power-consumption [filename]
  (let [raw-data (vec (get-resource-file-by-line filename))
        transposed-data (invert-lines raw-data)
        most-common-bit (Character/digit ^Character (first (first (reverse (sort-by val (frequencies transposed-data))))) 10)
        least-common-bit (map (fn [bit] (if (= bit 0) 1 0)) most-common-bit)
        ]
    (*
      (map binlist-to-decimal most-common-bit)
      (map binlist-to-decimal least-common-bit)
      )
    )
  )
