(ns re-demo.alert-box
  (:require [re-com.core   :refer [h-box v-box box line gap label alert-box alert-list]]
            [re-com.alert  :refer [alert-box-args-desc alert-list-args-desc]]
            [re-demo.utils :refer [panel-title component-title args-table github-hyperlink status-text paragraphs]]
            [reagent.debug :refer-macros [dbg prn println log dev? warn warn-unless]]
            [reagent.core  :as    reagent]))

(defn alert-box-demo
  []
  (let [show-alert  (reagent/atom true)
        show-alert1 (reagent/atom true)
        show-alert2 (reagent/atom true)]
    (fn []
      [v-box
       :size     "auto"
       :gap      "10px"
       :children [[panel-title [:span "[alert-box ... ]"
                                [github-hyperlink "Component Source" "src/re_com/alert.cljs"]
                                [github-hyperlink "Page Source"      "src/re_demo/alert_box.cljs"]]]

                  [h-box
                   :gap      "100px"
                   :children [[v-box
                               :gap      "10px"
                               :width    "450px"
                               :children [[component-title "Notes"]
                                          [status-text "Stable"]
                                          [paragraphs
                                           [:p "A component which renders a single bootstrap styled alert-box."]]
                                          [args-table alert-box-args-desc]]]
                              [v-box
                               :width    "600px"
                               :gap      "10px"
                               :children [[component-title "Demo"]
                                          (if @show-alert
                                            [alert-box      ;(alert-box-meta alert-box)
                                             :id         1
                                             :alert-type :info
                                             :heading    "This Is An Alert Heading"
                                             :body       [:p "This is an alert body. This alert has an :alert-type of :info which makes it blue, and it includes a :heading, a :body and a close button. Click the x to close it."]
                                             :closeable? true
                                             :on-close   #(reset! show-alert false)]
                                            [:p {:style {:text-align "center" :margin "30px"}} "[You closed me]"])
                                          [gap :size "50px"]
                                          [:p "Further Variations ..."]
                                          (when @show-alert1
                                            [:div
                                             [alert-box
                                              :alert-type :info
                                              :heading    "Alert with :heading but no :body"
                                              :closeable? true
                                              :on-close   #(reset! show-alert1 false)]])
                                          (when @show-alert2
                                            [:div
                                             [alert-box
                                              :alert-type :warning
                                              :body       "Alert with :body but no :heading (:padding set to 6px)."
                                              :padding    "6px"
                                              :closeable? true
                                              :on-close   #(reset! show-alert2 false)]])
                                          [alert-box
                                           :alert-type :danger
                                           :heading    ":alert-type is :danger"
                                           :body       [:span "This is the :body of an danger-styled alert with :closeable? omitted (defaults to false). "
                                                        [:a {:href "http://google.com" :target "_blank"} "Link to Google"] "."]]]]]]]])))



;; core holds onto references, so need one level of indirection to get figwheel updates
(defn panel
  []
  [alert-box-demo])

;;; TODO: For testing only - remove!
;(println "METATDATA for alert-box-demo:" (meta #'alert-box-demo))
;(println "goog.DEBUG-1:" ^boolean (.-DEBUG js/goog))
;(println "goog.DEBUG-2:" ^boolean js/goog.DEBUG)
;(when js/goog.DEBUG (println "It's TRUE"))
;(when ^boolean js/goog.DEBUG (println "It's TRUE"))
;
;(println "ADD-META-1: " (meta (add-meta {:aa "hello"})))
;
;(def aa (add-meta {:bb "goodbye"}))
;
;(println "ADD-META-2: file = '" (:file (meta aa)) "', line = " (:line (meta aa)))
;
;;(dbg alert-box-demo)
;(dbg 'alert-box-demo)
;(dbg #'alert-box-demo)
;
;;(set! (.-DEBUG js/goog) false)
;;(println "goog.DEBUG-2:" js/goog.DEBUG)
