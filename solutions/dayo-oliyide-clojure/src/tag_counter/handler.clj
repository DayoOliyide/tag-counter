(ns tag-counter.handler
  (:require [clojure.data.priority-map :refer [priority-map-by]]
            [compojure
             [core :refer :all]
             [route :as route]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [tag-counter.core :refer [parse-file]]))

(defn print-table-str [m n]
  (with-out-str
    (printf "=============The current top %s tags ==============\n" n)
    (run! #(printf "%s\t\t %s\n" (first %) (second %)) (take n m))))


(defn gen-top-fn [m n]
  (let [at-m (atom m)] ;;<------ ARGH!! State!!! I know I know TODO - Revisit
      (fn [f]
        (if f
          (do
            (swap! at-m #(merge-with + % (parse-file f)))  ;;<---- Bottle Neck?
            (print-table-str @at-m n))
          (print-table-str @at-m n)))))

(def top-ten-fn (gen-top-fn (priority-map-by >) 10))

(defroutes app-routes
  (GET "/" [] (top-ten-fn nil))
  (POST "/" [data] (top-ten-fn (:tempfile data)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes (assoc site-defaults :security false)))
