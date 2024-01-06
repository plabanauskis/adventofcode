(ns aoc.y2015.day-21
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as comb]))

(def ^:private shop
  {:weapons [{:cost 8 :damage 4 :armor 0}
             {:cost 10 :damage 5 :armor 0}
             {:cost 25 :damage 6 :armor 0}
             {:cost 40 :damage 7 :armor 0}
             {:cost 74 :damage 8 :armor 0}]
   :armor [{:cost 13 :damage 0 :armor 1}
           {:cost 31 :damage 0 :armor 2}
           {:cost 53 :damage 0 :armor 3}
           {:cost 75 :damage 0 :armor 4}
           {:cost 102 :damage 0 :armor 5}]
   :rings [{:cost 25 :damage 1 :armor 0}
           {:cost 50 :damage 2 :armor 0}
           {:cost 100 :damage 3 :armor 0}
           {:cost 20 :damage 0 :armor 1}
           {:cost 40 :damage 0 :armor 2}
           {:cost 80 :damage 0 :armor 3}]})

(defn- parse-input
  [input]
  {:hp (->> (str/split (first input) #" ") last Integer/parseInt)
   :damage (->> (str/split (second input) #" ") last Integer/parseInt)
   :armor (->> (str/split (nth input 2) #" ") last Integer/parseInt)})

(defn- collect-equipment
  []
  (let [weapons (comb/combinations (:weapons shop) 1)
        armors (concat [[]] (comb/combinations (:armor shop) 1))
        rings (concat [[]] (comb/combinations (:rings shop) 1) (comb/combinations (:rings shop) 2))]
    (for [w weapons
          a armors
          r rings]
      (concat w a r))))

(defn- fight
  [boss equipment]
  (letfn [(hit [attacker defender]
            (let [damage (max 1 (- (:damage attacker) (:armor defender)))]
              (update defender :hp - damage)))]
    (let [player {:hp 100
                  :damage (reduce + (map :damage equipment))
                  :armor (reduce + (map :armor equipment))}]
      (loop [boss boss
             player player
             player-turn? true]
        (cond
          (<= (:hp boss) 0) [equipment :player]
          (<= (:hp player) 0) [equipment :boss]
          :else (if player-turn?
                  (recur (hit player boss) player false)
                  (recur boss (hit boss player) true)))))))

(defn- equipment-cost
  [equipment]
  (reduce + (map :cost equipment)))

(defn part-1
  [input]
  (let [boss (parse-input input)]
    (->> (collect-equipment)
         (map (partial fight boss))
         (filter #(= :player (second %)))
         (map first)
         (map equipment-cost)
         (apply min))))

(defn part-2
  [input]
  (let [boss (parse-input input)]
    (->> (collect-equipment)
         (map (partial fight boss))
         (filter #(= :boss (second %)))
         (map first)
         (map equipment-cost)
         (apply max))))