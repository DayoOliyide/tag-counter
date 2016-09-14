(ns tag-counter.core
  (:require [clojure.data.priority-map :refer [priority-map-by]]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(defn clean-tag [w]
  (-> w
      string/trim
      string/lower-case
      (string/replace #"\p{Punct}" "")))


(defn line->tags [l]
  (as-> l $$
    (string/split $$ #"\s")
    (mapv clean-tag $$)
    (remove empty? $$)))

(defn update-map [m & tags]

  (let [reduce-fn (fn [mp tag]
                    (let [n (get mp tag 0)]
                      (assoc mp tag (inc n))))]
    (reduce reduce-fn m tags)))


(defn parse-file [f]
  (with-open [rdr (io/reader f)]
    (loop [lines (line-seq rdr)
           m (priority-map-by >)]
      (if (empty? lines)
        m
        (let [line (first lines)
              tags (line->tags line)]
          (recur (rest lines) (apply update-map m tags)))))))
