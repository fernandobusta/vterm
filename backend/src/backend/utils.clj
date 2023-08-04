(ns backend.utils
  (:require [clojure.tools.logging :refer :all]
            [clojure.java.io :as io]))

(defn check-content-type
  [ctx content-types]
  (try
    (if (#{:put :post} (get-in ctx [:request :request-method]))
      (some true?
            (let [content-type (get-in ctx [:request :headers "content-type"])]
              (map (fn [supported-type]
                     (.startsWith content-type supported-type))
                   content-types)))
      true)
    (catch Exception e (error e))))
