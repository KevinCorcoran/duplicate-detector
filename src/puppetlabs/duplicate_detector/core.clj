(ns puppetlabs.duplicate-detector.core
  (:gen-class)
  (:import (java.util.jar JarFile))
  (:require [clojure.tools.nrepl.server :refer [start-server stop-server]]
            [clojure.string :refer [split]]
            [clojure.java.io :refer [file]]
            [clojure.tools.cli :refer [cli]]
            [clojure.pprint :refer [pprint]]))

(def resources (atom {}))

(defn should-process?
  [resource]
  (let []
    (and
      ;; Fliter out clojure stdlib - it's really big
      (not (.startsWith resource "clojure/"))

      ;; we only care about .class and .clj files
      (or (.endsWith resource ".class") (.endsWith resource ".clj"))

      ;; don't care about anything in META-INF
      (not (.startsWith resource "META-INF"))

      ;; lein includes project.clj ... no thank you
      (not (= resource "project.clj")))))

(defn process-jar!
  [filename]
  (println "Processing .jar file: " filename)
  (let [jar (JarFile. filename)
        entries (.entries jar)]
    (while (.hasMoreElements entries)
      (let [resource (.getName (.nextElement entries))]
        (when (should-process? resource)
          ;(println "Found .jar entry: " resource)
          (if (contains? @resources resource)
            (throw (Exception.
                     (str "Class or namespace " resource " found in both " filename " and " (@resources resource))))
            (swap! resources assoc resource filename)))))))

;; We expect we are being run as an uberjar
;; Like this:
;;    java -jar <jar-name.jar> --plugins <plugins_directory>
(defn -main
  [& args]

  ;; uncomment 2 lines below to start a remote repl server
  ;(println "Starting nREPL server on port 7888...")
  ;(defonce server (start-server :port 7888))

  (let [
         ;; For whatever reason, this system property only returns the uberjar
         ;; and nont the entire classpath.
         uberjar (System/getProperty "java.class.path")

         cli-data (first (cli args ["-p" "--plugins" "plugins directory"]))
         plugins-dir (cli-data :plugins)
         plugin-jars (if plugins-dir
                       (filter #(.endsWith (.getPath %) ".jar") (file-seq (file plugins-dir)))
                       [])]
    (process-jar! uberjar)
    (doseq [f plugin-jars] (process-jar! f))
    (println "Resulting resource map is: ")
    (pprint @resources)))
