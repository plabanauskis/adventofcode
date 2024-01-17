(ns aoc.y2016.day-13
  (:require [clojure.data.priority-map :refer [priority-map]]
            [clojure.set :as cset]))

(def ^:dynamic destination [31 39])

(defn- count-bits
  [n]
  (loop [n n
         cnt 0]
    (if (zero? n)
      cnt
      (recur (bit-shift-right n 1)
             (if (pos? (bit-and n 1)) (inc cnt) cnt)))))

(defn- tile
  [[x y] fav]
  (if (even? (count-bits (+ (* x x) (* 3 x) (* 2 x y) y (* y y) fav)))
    :s
    :w))

(defn- neighbours
  [[x y] fav]
  (for [[x y] [[x (dec y)] [x (inc y)] [(dec x) y] [(inc x) y]]
        :when (and (not (neg? x))
                   (not (neg? y))
                   (= :s (tile [x y] fav)))]
    [x y]))

(defn part-1
  [input]
  (let [fav (Integer/parseInt input)
        start [1 1]]
    (loop [distances (priority-map start 0)
           visited #{start}]
      (when-let [[location distance] (peek distances)]
        (if (= location destination)
          distance
          (let [distances (pop distances)
                neighbour-locations (->> (neighbours location fav)
                                         (filter #(not (visited %))))
                neighbours-dist (mapcat #(vector % (inc distance)) neighbour-locations)]
            (if (empty? neighbours-dist)
              (recur distances (cset/union visited (set neighbour-locations)))
              (recur (apply assoc distances neighbours-dist) (cset/union visited (set neighbour-locations))))))))))

(defn part-2
  [input]
  (let [fav (Integer/parseInt input)
        start [1 1]]
    (loop [next-locations #{start}
           visited #{start}
           step 0]
      (if (= 50 step)
        (count visited)
        (let [next-locations (->> next-locations
                                  (mapcat #(neighbours % fav))
                                  (filter #(not (visited %)))
                                  set)]
          (recur next-locations
                 (cset/union visited next-locations)
                 (inc step)))))))