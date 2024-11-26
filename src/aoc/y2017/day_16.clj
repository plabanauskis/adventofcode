(ns aoc.y2017.day-16
  (:require [clojure.string]))

(def ^:dynamic programs "abcdefghijklmnop")

(defn- spin
  [size programs]
  (apply str (concat (take-last size programs) (drop-last size programs))))

(defn- exchange
  [a b programs]
  (apply str (-> programs
                 vec
                 (assoc a (nth programs b))
                 (assoc b (nth programs a)))))

(defn- partner
  [a b programs]
  (apply str (-> programs
                 vec
                 (assoc (clojure.string/index-of programs a)
                        (nth programs (clojure.string/index-of programs b)))
                 (assoc (clojure.string/index-of programs b)
                        (nth programs (clojure.string/index-of programs a))))))

(defn- parse-input
  [input]
  (->> (clojure.string/split input #",")
       (map (fn [move]
              (let [[_ op a b] (re-matches #"(\w)(\w+)\/?(\w+)?" move)]
                (case op
                  "s" (partial spin (Integer/parseInt a))
                  "x" (partial exchange (Integer/parseInt a) (Integer/parseInt b))
                  "p" (partial partner a b)))))))

(defn- perform-dance
  [moves programs]
  (reduce #(%2 %1) programs moves))

(defn part-1
  [input]
  (let [moves (parse-input input)]
    (perform-dance moves programs)))

(defn part-2
  [input]
  (let [moves (parse-input input)]
    (reduce (fn [[variants variants-inv i] new-var]
              (if (variants new-var)
                (let [cycle-ix (mod 1000000000 i)]
                  (reduced (variants-inv (dec cycle-ix))))
                [(conj variants [new-var i])
                 (conj variants-inv [i new-var])
                 (inc i)]))
            [{} {} 0]
            (drop 1 (iterate (partial perform-dance moves) programs)))))
