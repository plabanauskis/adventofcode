(ns aoc.y2016.day-17
  (:require [utils :refer [md5-hash]]
            [clojure.set :as cset]))

(defn- neighbour?
  [[x y] c]
  (and (>= 3 x 0) (>= 3 y 0)
       (#{\b \c \d \e \f} c)))

(defn- neighbours
  [[x y] pass path]
  (let [dir (take 4 (md5-hash (str pass (apply str path))))
        locations [[x (dec y)] [x (inc y)] [(dec x) y] [(inc x) y]]]
    (->> (map vector locations dir [\U \D \L \R])
         (filter #(apply neighbour? (butlast %)))
         (map #(hash-map :loc (first %) :dir (last %))))))

(defn- next-paths
  [pass {:keys [loc path]}]
  (map (fn [{:keys [loc dir]}]
         {:loc loc
          :path (conj path dir)})
       (neighbours loc pass path)))

(defn part-1
  [input]
  (let [dest [3 3]]
    (loop [paths [{:loc [0 0] :path []}]]
      (if-let [destination (some #(when (= (:loc %) dest) %) paths)]
        (apply str (:path destination))
        (recur (mapcat (partial next-paths input) paths))))))

(defn part-2
  [input]
  (let [dest [3 3]]
    (loop [paths [{:loc [0 0] :path []}]
           longest 0]
      (let [destinations (filter #(when (= (:loc %) dest) %) paths)
            rem-paths (clojure.set/difference (set paths) destinations)]
        (if (empty? paths)
          longest
          (recur (mapcat (partial next-paths input) rem-paths)
                 (if (empty? destinations)
                   longest
                   (->> destinations first :path count))))))))