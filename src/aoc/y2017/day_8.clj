(ns aoc.y2017.day-8)

(defn- parse-line
  [l]
  (let [[r op v pred-r pred-op pred-v] (->> l
                                            (re-matches #"(\w+) (\w+) (-?\d+) if (\w+) ([><=!]+) (-?\d+)")
                                            (drop 1))]
    [r op (Integer/parseInt v) pred-r pred-op (Integer/parseInt pred-v)]))

(defn- get-op
  [op]
  (case op
    "inc" +
    "dec" -
    ">" >
    "<" <
    ">=" >=
    "<=" <=
    "==" =
    "!=" not=))

(defn- build-fn
  [[r op v pred-r pred-op pred-v]]
  (let [op (get-op op)
        pred-op (get-op pred-op)]
    (fn [vm]
      (let [rv (get vm r 0)
            pred-rv (get vm pred-r 0)]
        (if (pred-op pred-rv pred-v)
          (assoc vm r (op rv v))
          vm)))))

(defn part-1
  [input]
  (->> input
       (map parse-line)
       (map build-fn)
       (reduce (fn [vm f] (f vm)) {})
       (vals)
       (apply max)))

(defn part-2
  [input]
  (->> input
       (map parse-line)
       (map build-fn)
       (reduce (fn [vms f] (conj vms (f (last vms)))) [{}])
       (mapcat vals)
       (apply max)))
