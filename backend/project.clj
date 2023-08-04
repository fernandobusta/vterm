(defproject backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :ring {:handler liberator-tutorial.core/handler}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/core.async "1.3.618"]
                 [clj-time "0.15.2"]
                 [liberator "0.15.3"]
                 [compojure "1.6.2"]
                 [ring/ring-core "1.8.2" :exclusions [clj-time]]
                 [ring/ring-anti-forgery "1.1.0"]
                 [ring-server "0.4.0"]
                 [cheshire "5.7.1"]
                 [ring/ring-defaults "0.3.0"]
                 [http-kit "2.5.0"]
                 [commons-daemon/commons-daemon "1.0.15"]
                 [com.stuartsierra/component "1.0.0"]
                 [slingshot "0.12.2"]
                 [org.clojure/data.json "0.2.6"]
                 [com.rpl/specter "1.1.3"]
                 [org.clojure/tools.logging "0.4.1"]
                 [com.lmax/disruptor "3.4.2"]
                 [org.apache.logging.log4j/log4j-slf4j-impl "2.17.1"]
                 [org.apache.logging.log4j/log4j-core "2.17.1"]
                 [org.apache.logging.log4j/log4j-1.2-api "2.17.1"]
                 [ring-cors "0.1.13"]
                 [ring/ring-json "0.5.1"]
                 [org.clojure/data.json "2.4.0"]]
  :main ^:skip-aot backend.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
