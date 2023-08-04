(ns backend.endpoint.data
  (:require [liberator.core :refer [resource defresource]]
            [clojure.tools.logging :refer [debugf info infof error errorf]]
            [backend.exceptions :as exceptions]
            [backend.utils :as utils]
            ))

(defresource data-resource
    []
    :available-media-types ["application/json"]
    :allowed-methods [:get]
    :known-content-type? #(utils/check-content-type % ["application/json"])
    :handle-ok (fn [_] {:data "This is some data!"})
    :handle-exception (fn [ctx] (exceptions/handle-exception ctx))
    )