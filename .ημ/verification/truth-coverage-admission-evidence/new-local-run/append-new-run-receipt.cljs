(ns new-run-evidence
  (:require [eta-mu.receipt-river.api :as api] ["node:fs" :as fs]))
(let [repo (first *command-line-args*)
      ts (.toISOString (js/Date.))
      payload {:ts ts :kind :test-run :repo "riatzukiza/Truth"
               :origin "truth-coverage-new-local-run-after-filesystem-loss"
               :owner "foresight_prs"
               :dod "Original full local coverage criterion; newly retained positive and hosted report evidence, no task transition"
               :pi "local-only evidence/decision preparation"
               :host "persistent independent accepted58 store; isolated JVM/home/cache/private literal tmp"
               :manifest [".ημ/verification/truth-coverage-new-local-run" ".ημ/session-mycology/ledger.md"]
               :refs ["58b0fd91ea65b7a4b3a1f571f22f3d394374a871" "b239e6bf1a43fcce64ccebf9234b0a1ff0734be1" "37532420467" "112557881933"]
               :tests "Whole original bin/coverage --text exit 0; 879 tests / 15,486 assertions / 0 failures / 0 errors; line 78.78% / form 84.96%. 300 prepared product files exact/trackedclean."
               :note "New actual stdout/stderr/reports retained; former local outputs remain unavailable. LCOV equal hosted, Codecov one positive hit counter differs, separate hashes retained. Bwrap network disabled, source/host RO, private home/Maven/JDK/tmp, no DISPLAY/credentials; no shared writes. Decision/nativecomment are DRAFTS held for peer; no board writes/push."
               :decisions "Preserve full original 3 DoD; no new protection/threshold policy activation. Recovery count scope: 78 command streams + 5 source-copy captures = 83 b64 files, not 83 independent observations."}
      event (api/build-event {:event-id "truth-coverage-new-local-run-ve63vqf3"
                              :recorded-at ts :component-manifest {:eta-mu/version "1.1.1"}
                              :command "bin/coverage --text unchanged accepted58"
                              :producer {:source/revision "154440f3c997aa9208194bba59b5edbef3654f78"}
                              :subject {:repo "riatzukiza/Truth" :purpose :evidence-recovery}}
                             payload)
      line (pr-str event)
      result (api/validate-line line 1)]
  (assert (:ok result) (pr-str (:errors result)))
  (fs/appendFileSync (str repo "/receipts.edn") (str line "\n"))
  (println "one valid declared test-run appended; no board operation"))
