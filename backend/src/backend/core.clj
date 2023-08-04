(ns backend.core
  (:require
    [backend.web-server :as ws]
    [backend.config :as config]
    [backend.routes :as routes]
    [clojure.tools.logging :as log])
  (:gen-class))

(def system nil)

(defn- create-system [config-filename]
  (component/system-map
    :config (component/using (config/->Configuration config-filename) [])
    :routes (component/using (routes/->Routes) [:config])
    :web-server (component/using (ws/map->WebServer {})
                                 [:config :routes])))

(defn start
  []
  (if system
    (try
      (alter-var-root #'system component/start)
      (catch Exception e (log/error e "Could not start system:")))
    (log/error "System not initialised. Start failed.")))

(defn stop
  []
  (log/info "Stopping Service.")
  (when system
    (alter-var-root #'system component/stop))
  (log/info "Stopped Service."))

(defn add-shutdown-hook
  []
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop)))

(defn -main
  [config-filename]
  (add-shutdown-hook)
  (log/info "Starting project-clj-api with config:" config-filename)
  (alter-var-root #'system (fn [_] (create-system config-filename)))
  (start)
  (log/info "Started project-clj-api..."))