(ns backend.web-server
  (:require [clojure.tools.logging :refer :all]
            [backend.routes :as routes]
            [org.httpkit.server :as server]
            [com.stuartsierra.component :as component]))

(defrecord WebServer [web-server standby-data]
  component/Lifecycle
  (start [component]
    (info "Starting Web Server component.")
    (let [config (-> component :config :config)
          web-server (->> (merge {:port (:port config)
                                  :host (:host config)
                                  :join? false
                                  :max-body 100000000
                                  :max-line 1000000}
                                 (:web-server config))
                          (server/run-server (routes/build-routes component)))]
      (info "Started Web Server component.")
      (assoc component :web-server web-server)))
  (stop [component]
    (info "Stopping Web Server component..")
    (if-let [web-server (:web-server component)]
      (web-server))
    (assoc component :web-server nil)))

(defn new-web-server [standby-data]
  (->WebServer nil standby-data))
