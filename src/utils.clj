(ns utils
  (:require [clojure.java.io :as io])
  (:import (java.security MessageDigest)))

(defn read-resource [filename]
  (with-open [rdr (io/reader (io/resource filename))]
    (doall (line-seq rdr))))

(defn md5-hash [s]
  (->> s
       .getBytes
       (.digest (MessageDigest/getInstance "MD5"))
       (map byte)
       (map #(format "%02x" %))
       (apply str)))
