(ns aoc.y2016.day-10
  (:require [clojure.string]))

(def ^:dynamic microchips #{17 61})

(defn- parse-line
  [l]
  (if (clojure.string/starts-with? l "value")
    (let [[_ value bot] (re-matches #".+?(\d+?) .+?(\d+)" l)]
      (vec (map #(Integer/parseInt %) [bot value])))
    (let [[_ bot outlow low outhigh high] (re-matches #".+?(\d+?) .+? (output|bot) (\d+?) .+? (output|bot) (\d+)" l)]
      [(Integer/parseInt bot)
       outlow
       (Integer/parseInt low)
       outhigh
       (Integer/parseInt high)])))

(defn- assign-chip
  [bots to chip]
  (if (get bots to)
    (update bots to conj chip)
    (assoc bots to #{chip})))

(defn part-1
  [input]
  (let [parsed (map parse-line input)
        {assignments 2 instructions 5} (group-by count parsed)
        outputs {}
        bots (reduce (fn [acc [bot value]]
                       (if (get acc bot)
                         (update acc bot conj value)
                         (assoc acc bot #{value})))
                     {}
                     assignments)
        bots (loop [bots bots
                    outputs outputs
                    instructions instructions]
               (let [[bot outlow low outhigh high] (first (filter (fn [[num & _]]
                                                     (= 2 (count (get bots num))))
                                          instructions))
                     chips (get bots bot)
                     min-chip (apply min chips)
                     max-chip (apply max chips)]
                 (if (= microchips chips)
                   bots
                   (let [bots (if (= "bot" outlow)
                                (-> bots
                                    (assign-chip low min-chip)
                                    (update bot disj min-chip))
                                bots)
                         bots (if (= "bot" outhigh)
                                (-> bots
                                    (assign-chip high max-chip)
                                    (update bot disj max-chip))
                                bots)
                         outputs (if (= "output" outlow)
                                   (assoc outputs low min-chip)
                                   outputs)
                         outputs (if (= "output" outhigh)
                                   (assoc outputs high max-chip)
                                   outputs)]
                     (recur bots outputs (filter #(not= [bot low high] %) instructions))))))]
    (->> bots
         (filter #(= microchips (second %)))
         ffirst)))

(defn part-2
  [input]
  (let [parsed (map parse-line input)
        {assignments 2 instructions 5} (group-by count parsed)
        outputs {}
        bots (reduce (fn [acc [bot value]]
                       (if (get acc bot)
                         (update acc bot conj value)
                         (assoc acc bot #{value})))
                     {}
                     assignments)
        outputs (loop [bots bots
                    outputs outputs
                    instructions instructions]
               (let [[bot outlow low outhigh high] (first (filter (fn [[num & _]]
                                                                    (= 2 (count (get bots num))))
                                                                  instructions))
                     chips (get bots bot)
                     min-chip (apply min chips)
                     max-chip (apply max chips)]
                 (if (and (get outputs 0) (get outputs 1) (get outputs 2))
                   [(get outputs 0) (get outputs 1) (get outputs 2)]
                   (let [bots (if (= "bot" outlow)
                                (-> bots
                                    (assign-chip low min-chip)
                                    (update bot disj min-chip))
                                bots)
                         bots (if (= "bot" outhigh)
                                (-> bots
                                    (assign-chip high max-chip)
                                    (update bot disj max-chip))
                                bots)
                         outputs (if (= "output" outlow)
                                   (assoc outputs low min-chip)
                                   outputs)
                         outputs (if (= "output" outhigh)
                                   (assoc outputs high max-chip)
                                   outputs)]
                     (recur bots outputs (filter #(not= [bot low high] %) instructions))))))]
    (* (get outputs 0) (get outputs 1) (get outputs 2))))