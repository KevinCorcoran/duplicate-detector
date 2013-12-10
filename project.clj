(defproject puppetlabs/duplicate-detector "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.nrepl "0.2.3"]]

  :profiles {:uberjar {:aot [puppetlabs.duplicate-detector.core]}}

  :main puppetlabs.duplicate-detector.core
  )
