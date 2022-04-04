(ns advent-of-code-2021.day2
  (:require [advent-of-code-2021.util :refer :all]
            [clojure.string :as str]
            ))

(defn find-position-product [file]
  (let [raw-instructions (vec (get-resource-file-by-line file))]
    )
  )

(defn parse-instructions [raw-instructions]
  (letfn [(parse-instruction [inst]
            (let [split (str/split inst #" ")
                  direction-map {"forward" :forward "down" :down "up" :up}
                  direction (get direction-map (get split 0))
                  distance (parse-int (get split 1))
                  ]
              (list direction distance)
              )
            )
          ]
    (map parse-instruction raw-instructions)
    )
  )