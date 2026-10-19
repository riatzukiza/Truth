(ns truth-sync-owned-receipt
 (:require [eta-mu.receipt-river.api :as api] ["node:fs" :as fs]))
(let [repo (first *command-line-args*)
      ts (.toISOString (js/Date.))
      payload {:ts ts :kind :decision :repo "riatzukiza/Truth"
               :origin "truth-accepted-upstream-sync-20261006"
               :owner "foresight_prs" :dod "Preserve accepted eight-commit ancestry and immutable existing source/board/ledger identities; local metadata-only candidate for independent peer"
               :pi "terminal upstream-sync; no feature/deploy activation"
               :host "isolated private store/worktree"
               :manifest ["docs/notes/truth-personal-upstream-sync-20261006.md" ".ημ/verification/truth-accepted-sync" ".ημ/session-mycology/ledger.md" "receipts.edn"]
               :refs ["b76e87381c3ed39c66059236bb14843cecc85ff7" "58b0fd91ea65b7a4b3a1f571f22f3d394374a871" "154440f3c997aa9208194bba59b5edbef3654f78"]
               :note "Fresh native fork/main/PR guards; exact twelve caller-workflow versions and pinned callee inspected; no eager merge invocation, native allow_auto_merge false. Local preparation only. Sparse complete tree identities do not assert all media content reviewed. Historical receipts preserved, not globally revalidated. Network-failed reads retained; no whole-suite/performance/test qualification claim."}
      event (api/build-event {:event-id "truth-sync-local-provenance-20261006-83u16qs3"
                              :recorded-at ts :component-manifest {:eta-mu/version "1.1.1"}
                              :command "owned local sync provenance"
                              :producer {:source/revision "154440f3c997aa9208194bba59b5edbef3654f78"}
                              :subject {:repo "riatzukiza/Truth" :purpose :upstream-sync}}
                             payload)
      line (pr-str event)
      result (api/validate-line line 1)]
 (assert (:ok result) (pr-str (:errors result)))
 (fs/appendFileSync (str repo "/receipts.edn") (str line "\n"))
 (println (js/JSON.stringify #js {:kind "decision" :schemaDeclared true :errors #js [] :appended 1})))
