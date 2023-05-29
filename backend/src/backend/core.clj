(ns backend.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes ANY]])
  (:gen-class))


(defroutes app
  (ANY "/" [] (resource)))

(def handler 
  (-> app 
      wrap-params))


(defn -main
  [& args]
  (println "Hello"))
