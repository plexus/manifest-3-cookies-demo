(ns demo.popup)

(def out-el (js/document.getElementById "cookies"))

(defn get-cookies [event]
  (js/console.log "get-cookies" event)
  (js/chrome.tabs.query
   (clj->js {:active true :lastFocusedWindow true})
   (fn [tabs]
     (js/console.log "tabs" tabs)
     (.then (js/chrome.cookies.getAll
             #js{:domain
                 (.-host (js/URL. (.-url (aget tabs 0))))})
            (fn [cookies]
              (set! (.-innerHTML out-el) "")
              (doseq [cookie cookies]
                (let [li (js/document.createElement "li")]
                  (.appendChild li
                                (js/document.createTextNode
                                 (str (.-name cookie) ": " (.-value cookie))))
                  (.appendChild out-el li))))))))

(defn init []
  (-> (js/document.getElementById "getCookies")
      (.addEventListener "click" get-cookies))

  (js/console.log "popup init done"))
