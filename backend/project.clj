(defproject backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :plugins [[lein-ring "0.12.2"]]
  :ring {:handler liberator-tutorial.core/handler}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring/ring-core "1.8.0"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [ring/ring-defaults "0.3.2"]
                 [compojure "1.6.2"]
                 [http-kit "2.5.0"]
                 [liberator "0.15.3"]
                ;;  ; Our Http library for client/server
                ;;  [http-kit "2.3.0"]
                ;;  ; Ring defaults - for query params etc
                ;;  [ring/ring-defaults "0.3.2"]
                ;;  ; Clojure data.JSON library
                ;;  [org.clojure/data.json "0.2.6"]
                 ]
  :main ^:skip-aot backend.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
