(ns advent-of-code-2021.util
  (:require [clojure.test :refer :all]
            [clojure.string :as str]))

(defn get-resource-file-by-line [file]
  (str/split-lines (slurp (.getPath (clojure.java.io/resource file)))))

(defn parse-int [s]
  (Integer/parseInt (re-find #"\d+" s)))

