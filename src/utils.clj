(ns utils
  (:require [clojure.java.io :as io]))

(defn read-resource [filename]
  (with-open [rdr (io/reader (io/resource filename))]
    (doall (line-seq rdr))))
