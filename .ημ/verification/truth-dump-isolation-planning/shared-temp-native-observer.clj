(require '[clojure.java.io :as io] '[clojure.edn :as edn] '[infra.dev.window-test])
(let [writer @#'infra.dev.window.loop/dump-error-artifacts!
      run-a (writer {:tick 101 :fictional-run "A" :ledger {:events []}})
      run-b (writer {:tick 202 :fictional-run "B" :ledger {:events []}})
      a (mapv run-a [:world-path :ledger-path :meta-path])
      b (mapv run-b [:world-path :ledger-path :meta-path])
      unrelated "/tmp/gates-of-truth/dumps/unrelated-negative-control.edn"
      fixtures (:clojure.test/each-fixtures (meta (find-ns 'infra.dev.window-test)))
      before (fn [paths] (mapv #(.exists (io/file %)) paths))]
  (assert (not (:error run-a))) (assert (not (:error run-b)))
  (assert (= 1 (count fixtures)))
  (spit unrelated "{:fictional true :owner :unrelated-control}")
  (assert (every? true? (before a))) (assert (every? true? (before b)))
  (println (pr-str {:observation :before :logical-run-A-files 3 :logical-run-B-files 3 :other-prefix-control true :real-host-inputs 0 :actual-source-writer true}))
  ((first fixtures) (fn [] nil))
  (let [after-a (before a) after-b (before b) control (.exists (io/file unrelated))]
    (println (pr-str {:observation :after-unchanged-A-fixture-noop :logical-run-A-present after-a :logical-run-B-present after-b :other-prefix-control-present control :real-host-inputs 0 :no-software-test-executed true :two-fictional-logical-runs-only true}))
    (assert (every? false? after-a)) (assert (every? false? after-b)) (assert control)))
