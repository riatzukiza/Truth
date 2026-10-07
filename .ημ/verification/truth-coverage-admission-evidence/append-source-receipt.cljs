(ns coverage-evidence-source-receipt
  (:require [eta-mu.receipt-river.api :as api] ["node:fs" :as fs]))
(let [repo (first *command-line-args*) ts (.toISOString (js/Date.))
      payload {:ts ts :kind :decision :repo "riatzukiza/Truth"
               :origin "truth-coverage-admission-evidence-after-filesystem-loss"
               :owner "foresight_prs"
               :dod "Preserve existing ready coverage task estimate2 and all3 original criteria; record new full local and actual hosted coverage, deliberate admission proposal"
               :pi "Coverage evidence decision; local candidate held for peer, no source implementation or promotion"
               :host "NEW independent persistent personal source store/worktree; separate accepted58 isolated test runtime"
               :manifest ["docs/notes/truth-coverage-admission-decision.md" "kanban/tasks/ci-coverage-workflow-broken.md" "kanban/tasks/.events/ledger.edn" ".ημ/verification/truth-coverage-admission-evidence" ".ημ/session-mycology/ledger.md"]
               :refs ["b239e6bf1a43fcce64ccebf9234b0a1ff0734be1" "58b0fd91ea65b7a4b3a1f571f22f3d394374a871" "tasks-ci-coverage-workflow-broken-1791335352661" "614276c8-c159-410a-9989-cdb58f65e69b" "37532420467" "112557881933"]
               :tests "NEW original whole bin/coverage --text at exact58 exit0:879tests15486assertions0fail/errors,line78.78/form84.96;300preparedproductfiles exact. Native owner help/usage/before/add-comment/after/events reads exit0, add-comment oktrue."
               :note "ONE actual installed-native comment in owned candidate; ready/estimate2/UUID/alloriginal nativevalues/body unchanged except enginewrite-id. Nativefrontmatter serialization changes rawcardprefix (FALSE), originalbytes archived; never manuallyrestored. Ledger1064-row prefix exact plusONEcomment event. Newlocal andhosted report hashes retained:LCOVsame, Codecovonepositivehitcounter differs. Former5c5/6125 absent, no recreatedoutputs. No protection/settings/sourcecode/pin/review/remote writes."
               :decisions "Propose retain successful workflow and failclosed process deterministic execution; percentagesreporting, no new numericthreshold/protectedcontext activation. All3DoD remainunchecked and proposalheld for revieweddisposition; no lifecycletransition."}
      event (api/build-event {:event-id "truth-coverage-admission-evidence-nhefrfek"
                              :recorded-at ts :component-manifest {:eta-mu/version "1.1.1"}
                              :command "owned native add-comment and coverage evidence decision"
                              :producer {:source/revision "154440f3c997aa9208194bba59b5edbef3654f78"}
                              :subject {:repo "riatzukiza/Truth" :purpose :coverage-evidence}}
                             payload)
      line (pr-str event) result (api/validate-line line 1)]
  (assert (:ok result) (pr-str (:errors result)))
  (fs/appendFileSync (str repo "/receipts.edn") (str line "\n"))
  (println "one valid declared decision receipt appended"))
