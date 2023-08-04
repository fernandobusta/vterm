(ns backend.exceptions
  (:require
    [clojure.tools.logging :refer [error]]
    [liberator.representation :refer [ring-response]]))

(defn handle-exception [ctx]
  (let [exception (:exception ctx)
        message (.getMessage ^Throwable exception)]
    (ring-response
      {:status 500
       :body (pr-str message)
       :headers {"Content-Type" "application/json"}})))
