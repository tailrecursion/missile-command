#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.5.0"

(set-env!
  :project	'missile-command
  :version	"0.1.1-SNAPSHOT"
  :dependencies (read-string (slurp "deps.edn"))
  :out-path     "resources/public"
  :src-paths    #{"src"})

(add-sync! (get-env :out-path) #{"resources/assets"})

(require
 '[tailrecursion.hoplon.boot      :refer :all]
 '[tailrecursion.boot.task        :refer :all]
 '[tailrecursion.boot.task.notify :refer [hear]]
 '[tailrecursion.boot.task.ring   :refer [dev-server]])

(deftask dev
  "Build for development"
  [& args]
  (comp
   (watch)
   (hear)
   (hoplon {:prerender false})
   (dev-server)))
