(ns demo.sw
  (:require [shadow.cljs.modern :refer (js-await)]))

(defn init []
  (js/console.log "sw init done"))
