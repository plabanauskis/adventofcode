(ns aoc.y2024.day-9)

(defn- parse-input
  [input]
  (->> input
       (re-seq #"\d")
       (map parse-long)))

(defn- represent-disk
  [blocks]
  (->> blocks
       (map-indexed vector)
       (mapcat (fn [[ix space]]
                 (repeat space (when (even? ix) (/ ix 2)))))
       vec))

(defn- compact-fragments
  [disk]
  (let [file-blocks-count (count (remove nil? disk))]
    (loop [disk disk]
      (let [first-empty-ix (.indexOf disk nil)
            last-file-ix (last (keep-indexed (fn [i x] (when x i)) disk))]
        (if (= last-file-ix (dec file-blocks-count))
          disk
          (recur (assoc disk
                   first-empty-ix (nth disk last-file-ix)
                   last-file-ix nil)))))))

(defn- checksum
  [disk]
  (reduce-kv (fn [chk ix id]
               (+ chk (* ix (or id 0))))
             0
             disk))

(defn part-1
  [input]
  (->> input
       parse-input
       represent-disk
       compact-fragments
       checksum))

(defn- get-file-size
  [disk file-ix]
  (- (inc (.lastIndexOf disk file-ix))
     (.indexOf disk file-ix)))

(defn- find-free-space
  [disk size before-position]
  (let [relevant-part (take before-position disk)
        partitions (partition size 1 relevant-part)]
    (->> partitions
         (keep-indexed (fn [ix part]
                         (when (every? nil? part) ix)))
         first)))

(defn- move-file-to-front
  [disk file-ix]
  (let [file-size (get-file-size disk file-ix)
        file-location (.indexOf disk file-ix)
        free-space-location (find-free-space disk file-size file-location)]
    (if free-space-location
      (vec (concat
             (subvec disk 0 free-space-location)
             (repeat file-size file-ix)
             (subvec disk (+ free-space-location file-size) file-location)
             (repeat file-size nil)
             (subvec disk (+ file-location file-size))))
      disk)))

(defn- compact-files
  [disk]
  (let [last-file-ix (last (remove nil? disk))]
    (loop [disk disk
           file-ix last-file-ix
           moved-files #{}]
      (if (neg? file-ix)
        disk
        (recur (if (moved-files file-ix)
                 disk
                 (move-file-to-front disk file-ix))
               (dec file-ix)
               (conj moved-files file-ix))))))

(defn part-2
  [input]
  (->> input
       parse-input
       represent-disk
       compact-files
       checksum))
