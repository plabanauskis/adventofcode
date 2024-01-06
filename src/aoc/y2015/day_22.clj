(ns aoc.y2015.day-22
  (:require [clojure.data.priority-map :refer [priority-map]]
            [clojure.string :as str]
            [clojure.set :as cset]))

(def ^:dynamic player {:hp 50 :mana 500})

(def ^:private spells
  {:magic-missile {:cost  53
                   :cast  (fn [game]
                            (-> game
                                (update :player-mana #(- % 53))
                                (update :boss-hp #(max 0 (- % 4)))))
                   :apply identity}
   :drain         {:cost  73
                   :cast  (fn [game]
                            (-> game
                                (update :player-mana #(- % 73))
                                (update :boss-hp #(max 0 (- % 2)))
                                (update :player-hp #(+ % 2))))
                   :apply identity}
   :shield        {:cost  113
                   :cast  (fn [game]
                            (if (pos? (:shield game))
                              game
                              (-> game
                                  (update :player-mana #(- % 113))
                                  (assoc :shield 6))))
                   :apply (fn [game]
                            (if (pos? (:shield game))
                              (update game :shield dec)
                              game))}
   :poison        {:cost  173
                   :cast  (fn [game]
                            (if (pos? (:poison game))
                              game
                              (-> game
                                  (update :player-mana #(- % 173))
                                  (assoc :poison 6))))
                   :apply (fn [game]
                            (if (pos? (:poison game))
                              (-> game
                                  (update :boss-hp #(max 0 (- % 3)))
                                  (update :poison dec))
                              game))}
   :recharge      {:cost  229
                   :cast  (fn [game]
                            (if (pos? (:recharge game))
                              game
                              (-> game
                                  (update :player-mana #(- % 229))
                                  (assoc :recharge 5))))
                   :apply (fn [game]
                            (if (pos? (:recharge game))
                              (-> game
                                  (update :player-mana #(+ % 101))
                                  (update :recharge dec))
                              game))}})

(defn- apply-spells
  [game]
  (-> game
      ((get-in spells [:shield :apply]))
      ((get-in spells [:poison :apply]))
      ((get-in spells [:recharge :apply]))))

(defn- fight
  [boss hard?]
  (letfn [(boss-turn [game]
            (if (zero? (:boss-hp game))
              game
              (let [armor? (pos? (:shield game))
                    game (apply-spells game)]
                (if (zero? (:boss-hp game))
                  game
                  (let [boss-moved (update game :player-hp #(- % (max 1 (- (:damage boss) (if armor? 7 0)))))
                        boss-moved (if hard? (update boss-moved :player-hp dec) boss-moved)]
                    (when (pos? (:player-hp boss-moved))
                      boss-moved))))))]
    (loop [games (priority-map {:player-mana (:mana player)
                                :player-hp   (if hard? (dec (:hp player)) (:hp player))
                                :boss-hp     (:hp boss)
                                :shield      0
                                :poison      0
                                :recharge    0}
                               0)]
      (when-let [game (peek games)]
        (let [[game used-mana] game
              games (pop games)
              game (apply-spells game)
              active-spells (->> game
                                 (filter #(and (int? (second %))
                                               (pos? (second %))))
                                 (map first)
                                 set
                                 (cset/intersection (set (keys spells))))]
          (if (zero? (:boss-hp game))
            used-mana
            (let [candidate-games (for [spell (->> spells
                                                   (filter (fn [[name _]] (not (active-spells name))))
                                                   (map second))
                                        :let [player-moved ((:cast spell) game)
                                              boss-moved (boss-turn player-moved)]
                                        :when (and (>= (:player-mana game) (:cost spell))
                                                   boss-moved)]
                                    [boss-moved (+ used-mana (:cost spell))])]
              (if (seq candidate-games)
                (recur (apply assoc games (flatten candidate-games)))
                (recur games)))))))))

(defn- parse-input
  [input]
  {:hp     (->> (str/split (first input) #" ") last Integer/parseInt)
   :damage (->> (str/split (second input) #" ") last Integer/parseInt)})

(defn part-1
  [input]
  (let [boss (parse-input input)]
    (fight boss false)))

(defn part-2
  [input]
  (let [boss (parse-input input)]
    (fight boss true)))