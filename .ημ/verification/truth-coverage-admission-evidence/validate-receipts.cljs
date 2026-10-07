(ns own-receipt-validation
 (:require [eta-mu.receipt-river.api :as api]
           [clojure.string :as str]
           ["node:fs" :as fs]
           ["node:child_process" :as child]))
(let [[repo revision base expected] *command-line-args*
      _ (assert (re-matches #"[0-9a-f]{40}" base))
      _ (assert (or (= "WORKTREE" revision) (re-matches #"[0-9a-f]{40}" revision)))
      old (child/execFileSync "git" #js ["-C" repo "show" (str base ":receipts.edn")] #js {:encoding "utf8"})
      current (if (= "WORKTREE" revision)
                (fs/readFileSync (str repo "/receipts.edn") "utf8")
                (child/execFileSync "git" #js ["-C" repo "show" (str revision ":receipts.edn")] #js {:encoding "utf8"}))
      _ (assert (str/starts-with? current old))
      old-count (count (str/split-lines old))
      lines (str/split-lines current)
      results (mapv (fn [index]
                      (let [r (api/validate-line (nth lines index) (inc index))]
                        (assert (:ok r) (pr-str (:errors r)))
                        (assert (= :declared (get-in r [:source/schema :status])))
                        {:line-number (inc index) :ok (:ok r)
                         :schema-id (str (get-in r [:event :event/schema :id]))
                         :schema-version (get-in r [:event :event/schema :version])
                         :event-id (get-in r [:event :event/id])
                         :kind (str (get-in r [:event :event/payload :kind]))
                         :repo (get-in r [:event :event/payload :repo])
                         :errors (:errors r)}))
                    (range old-count (count lines)))]
 (assert (= (js/parseInt expected 10) (count results)))
 (println (js/JSON.stringify (clj->js {:revision revision :base base :prefix-preserved true
       :base-rows old-count :total-rows (count lines) :new-owned-declared results}))))
