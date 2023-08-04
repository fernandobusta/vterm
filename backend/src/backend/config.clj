(ns backend.config
  (:require
    [clojure.tools.logging :refer :all]
    [com.stuartsierra.component :as component]))

(def default-configuration
  {:web-server {:host "0.0.0.0"
                :port 3444
                :daemon? true
                :join? false}})

(defrecord Configuration [filename]
  component/Lifecycle
  (start [this]
    (debug "Loading configuration")
    (assoc this :config default-configuration))
  (stop [this]
    (debug "Removing configuration.")
    (dissoc this :config)))
