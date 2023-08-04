(ns backend.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [backend.endpoint.data :as data]
            [com.stuartsierra.component :as component]
            [clojure.tools.logging :refer [debugf info infof error errorf]]
            [liberator.core :refer [resource defresource]]
            [liberator.representation :refer [ring-response as-response]]
            [compojure.core :refer [routes ANY GET POST PUT DELETE OPTIONS PATCH]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))


(def cors-headers
  "Generic CORS headers"
  {"Access-Control-Allow-Origin"  "*"
   "Access-Control-Allow-Headers" "*"
   "Access-Control-Allow-Methods" "GET, POST"})

(defn wrap-cors
  "Allow requests from all origins - also check preflight"
  [handler]
  (fn [request]
    (let [response (handler request)]
      (update-in response [:headers]
                merge cors-headers ))))

(defn build-routes
  [components]
    (let [config-component (:config component)]
    (routes 
      (ANY "/data" []
        (data/data-resources))
      (route/resources "/")
      (route/not-found "Not Found"))))

(defn app
  [component]
  (-> (build-routes)
      (wrap-cors)))

(defrecord Routes []
  component/Lifecycle
  
  (start
    [component]
    (info "Creating Routes.")
    (let [component (assoc component :app (app component))]
      (info "Created Routes.")
      component))
  
  (stop
    [component]
    (dissoc component :app)))