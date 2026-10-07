(ns contained-observer (:require [clojure.java.io :as io]))
(def root "/home/err/.codex/parallel-goal/issues-20261006/truth2-runtime-recovery-7585488fc2")
(defn install-ns! [n vars]
  (create-ns n)
  (doseq [[k v] vars] (intern n k v)))
(install-ns! 'contained.window {'service-state (atom nil)})
(install-ns! 'contained.ecs {'get-component (fn [& _] nil)})
(install-ns! 'contained.c {'radius :radius 'position :position})
(install-ns! 'contained.camera {'phase0-view-scale 1.0 'make-camera (fn [d] {:distance d}) 'update-camera-position identity})
(install-ns! 'contained.menu {'apply-action (fn [cfg _] cfg)})
(install-ns! 'contained.arc {'tick-genesis identity})
(install-ns! 'contained.tracking {'fit-all-bounds (fn [& _] {:radius 1.0 :center [0 0 0]}) 'bodies->render (fn [& _] []) 'distance-for-radius (fn [& _] 2.0)})
(install-ns! 'contained.rinput {'action-palette []})
(doseq [[a n] {'window 'contained.window 'ecs 'contained.ecs 'c 'contained.c 'camera 'contained.camera 'menu 'contained.menu 'arc 'contained.arc 'tracking 'contained.tracking 'rinput 'contained.rinput}] (alias a n))
(defn scenario-world [id] {:world {:demo/scenario id :arc/current :fictional}})
(defn action-row-state [& _] {:amount 0 :cost 0 :available? false})
(defn afford-colors [& _] {:label [0 0 0 1] :cost [0 0 0 1]})
(defn load-target-form! [file symbol]
 (with-open [r (java.io.PushbackReader. (io/reader file))]
  (loop []
   (let [form (read {:eof ::eof} r)]
    (when (= ::eof form) (throw (ex-info "Form not found" {:symbol symbol})))
    (if (and (seq? form) (= 'defn (first form)) (= symbol (second form)))
      (eval form)
      (recur))))))
(load-target-form! (str root "/worktree/dev/demo.clj") 'select!)
(load-target-form! (str root "/worktree/dev/demo.clj") 'prepare-formation!)
(load-target-form! (str root "/worktree/src/infra/render/hud.clj") 'controls-hud)
(defn invoke [which] (if (= which :select) (select! :nebula) (prepare-formation!)))
(def observations
 (vec
  (for [which [:select :formation] prior [{:tick-fn inc :on-step dec :mode :manual} {:tick-fn nil :mode :manual} {:mode :manual}]]
   (let [config (atom prior) failure (ex-info "fictional failed world read" {})
         world (reify clojure.lang.IDeref (deref [_] (swap! config assoc :independent-change :preserved) (throw failure)))
         svc {:config config :world world :world-intents (atom nil) :camera (atom {})}]
    (reset! contained.window/service-state svc)
    (let [thrown (try (invoke which) nil (catch Throwable t t))
          restored (= (select-keys prior [:tick-fn :on-step]) (select-keys @config [:tick-fn :on-step]))]
     (assert (identical? thrown failure))
     (assert (= :preserved (:independent-change @config)))
     (assert (= restored (= which :formation)))
     {:function which :prior-keys (vec (sort (keys prior))) :original-error-propagated true :pause-settings-restored restored :independent-change-preserved true})))))
(println (pr-str {:scope :exact-accepted-function-forms-with-synthetic-boundaries :failed-read-observations observations}))
(doseq [which [:select :formation]]
 (let [prior {:tick-fn inc :on-step dec :mode :manual} config (atom prior) svc {:config config :world (atom {}) :world-intents (atom nil) :camera (atom {})}]
  (reset! contained.window/service-state svc)
  (.interrupt (Thread/currentThread))
  (try
   (let [thrown (try (invoke which) nil (catch Throwable t t)) restored (= (select-keys prior [:tick-fn :on-step]) (select-keys @config [:tick-fn :on-step]))]
    (assert (instance? InterruptedException thrown)) (assert (= restored (= which :formation)))
    (println (pr-str {:scope :interrupted-wait :function which :interruption-propagated true :pause-settings-restored restored})))
   (finally (Thread/interrupted)))))
(doseq [which [:select :formation]]
 (let [config (atom {:tick-fn inc :on-step dec}) world (atom {}) intents (atom nil) svc {:config config :world world :world-intents intents :camera (atom {})}]
  (add-watch intents ::fictional-accept (fn [_ _ _ queued] (reset! world queued)))
  (reset! contained.window/service-state svc)
  (let [result (invoke which)]
   (assert (if (= which :formation) (:paused? result) (= :nebula (:scenario result))))
   (println (pr-str {:scope :successful-handoff-positive-control :function which :completed true})))))
(let [width 320.0 result (controls-hud {} width 240.0) passive (second (:text result))]
 (assert (= 352.0 (:x passive))) (assert (> (:x passive) width))
 (println (pr-str {:scope :exact-controls-hud-form-with-empty-fictional-action-palette :framebuffer-width width :passive-start-x (:x passive) :start-outside-frame true :font-or-rendering-claim false})))
