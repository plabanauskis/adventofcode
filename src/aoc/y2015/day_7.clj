(ns aoc.y2015.day-7
  (:require [clojure.string :as str]))

(defn- parse-line
  [l]
  (let [[expr res] (->> l
                        (re-matches #"(.+?) -> (\w+)")
                        (drop 1))]
    {res expr}))

(defn- get-vars
  [expr]
  (->> expr
       (re-matches #"(\w+?) .+? (\w+)")
       (drop 1)))

(defn- get-var
  [expr]
  (->> expr
       (re-matches #".+? (\w+?)")
       second))

(defn- evaluate-2-var
  [expr res f]
  (let [[l r] (get-vars expr)
        l (or (res l) (Integer/parseInt l))
        r (or (res r) (Integer/parseInt r))]
    (f l r)))

(defn- evaluate-1-var
  [expr res f]
  (let [var (get-var expr)
        var (or (res var) (Integer/parseInt var))]
    (f var)))

(defn- evaluate
  [expr res]
  (cond
    (str/includes? expr "AND") (evaluate-2-var expr res bit-and)

    (str/includes? expr "OR") (evaluate-2-var expr res bit-or)

    (str/includes? expr "LSHIFT") (evaluate-2-var expr res bit-shift-left)

    (str/includes? expr "RSHIFT") (evaluate-2-var expr res bit-shift-right)

    (str/includes? expr "NOT") (evaluate-1-var expr res #(bit-and (bit-not %) 0xFFFF))

    :else (or (res expr) (Integer/parseInt expr))))

(defn- signalled?
  [res [_ expr]]
  (cond
    (re-matches #"\w+? (AND|OR|LSHIFT|RSHIFT) \w+" expr)
    (let [[l r] (get-vars expr)]
      (and (or (re-matches #"\d+?" l) (res l))
           (or (re-matches #"\d+?" r) (res r))))

    (re-matches #"NOT \w+" expr)
    (let [var (get-var expr)]
      (or (re-matches #"\d+?" var) (res var)))

    :else
    (or (re-matches #"\d+?" expr) (res expr))))

(defn- solve
  [gates]
  (loop [res {}
         gates gates]
    (let [[resvar expr] (first (filter (partial signalled? res) gates))]
      (if (= "a" resvar)
        (evaluate expr res)
        (recur (assoc res resvar (evaluate expr res))
               (dissoc gates resvar))))))

(defn part-1
  [input]
  (solve (->> input
              (map parse-line)
              (reduce into {}))))

(defn part-2
  [input]
  (let [gates (->> input
                   (map parse-line)
                   (reduce into {}))
        a (solve gates)
        gates (assoc gates "b" (str a))]
    (solve gates)))