(ns advent-of-code-2021.day2
  (:require [advent-of-code-2021.util :refer :all]
            [clojure.string :as str]
            ))

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

(defn find-position-product [file]
  (let [raw-instructions (vec (get-resource-file-by-line file))
        instructions (parse-instructions raw-instructions)
        coordinate-list (map
                          (fn [instruction]
                            (case (first instruction)
                              :forward (list (second instruction) 0)
                              :down (list 0 (second instruction))
                              :up (list 0 (- (second instruction)))
                              )
                            )
                          instructions
                          )
        final-vector (reduce
                       (fn [vector coordinate]
                         (list (+ (first vector) (first coordinate)) (+ (second vector) (second coordinate)))
                         )
                       coordinate-list
                       )
        ]
    (* (first final-vector) (second final-vector))
    )
  )

(defn move-with-aim
  ([instructions]
   (move-with-aim instructions {:dist 0 :depth 0 :aim 0}))
  ([instructions course]
   (letfn [(process-instruction [instruction course]
             (case (first instruction)
               :forward {:dist  (+ (:dist course) (second instruction))
                         :depth (+ (:depth course) (* (:aim course) (second instruction)))
                         :aim   (:aim course)
                         }
               :down {:dist  (:dist course)
                      :depth (:depth course)
                      :aim   (+ (:aim course) (second instruction))
                      }
               :up {:dist  (:dist course)
                    :depth (:depth course)
                    :aim   (- (:aim course) (second instruction))
                    }
               )
             )
           ]
     (if (empty? instructions)
       course
       (recur (rest instructions)
              (process-instruction (first instructions) course))
       )
     )
   )
  )

(defn find-position-product-with-aim [file]
  (let [raw-instructions (vec (get-resource-file-by-line file))
        instructions (parse-instructions raw-instructions)
        final-vector (move-with-aim instructions)
        ]
    (* (:dist final-vector) (:depth final-vector))
    )
  )


