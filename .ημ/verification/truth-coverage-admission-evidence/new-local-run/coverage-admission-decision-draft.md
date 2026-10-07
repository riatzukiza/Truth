# Truth coverage admission: fresh recovery evidence and proposed decision

This is newly authored evidence after a filesystem-loss recovery. It does not recreate the lost local candidate, stdout, receipts, or report provenance. It proposes a coverage decision for the existing `ci-coverage-workflow-broken` task; the board remains ready, estimate 2, with its full original criteria. No task transition, checklist update, protection change, or publication is implied.

## Source and original outcome

Accepted upstream main is `58b0fd91ea65b7a4b3a1f571f22f3d394374a871`. The personal upstream-sync candidate is `b239e6bf1a43fcce64ccebf9234b0a1ff0734be1`, based on personal main `b76e87381c3ed39c66059236bb14843cecc85ff7`. The isolated recovery run executes the original accepted `bin/coverage --text` at the full accepted SHA, without source changes, test selection, namespace filters, or provider execution.

The existing card is `kanban/tasks/ci-coverage-workflow-broken.md`, explicit UUID `ci-coverage-workflow-broken`. Its historical July account reports `ClassNotFoundException: TablesExtension` and describes an ignored, nonrequired coverage failure. That account is retained verbatim. A current successful run establishes current execution, not the causal commit that repaired that historical symptom. Accepted `deps.edn` already declares commonmark and commonmark-ext-gfm-tables 0.21.0; this evidence slice makes no dependency edit.

The three original completion criteria remain:

- [ ] `bin/coverage --text` succeeds locally.
- [ ] The `coverage` workflow is green on `main`, or the workflow is deleted with a
      note saying why.
- [ ] If it is kept and made green, decide deliberately whether it should become a
      required check (see `docs/STATIC-ANALYSIS.md` › The gating contract for the
      argument that a non-gating check decays).

## Fresh local result

The new whole `bin/coverage --text` subprocess exited 0. Its actual output reports 879 tests / 15,486 assertions / 0 failures / 0 errors; line coverage is 78.78% and form coverage is 84.96%. The full stdout is 31,913 bytes, SHA256 `53b16715ac0d9eaf4d1dfe4317cfc2f3b1a56a44d8fc186abdbb815c47f0c2f1`; full stderr is 3,260 bytes, SHA256 `d0b9b79ac293a9b3c42152679937eccd5c81dba0b13ae9f476fcd5d1820841eb`. Stderr retains the JVM options notice and intentional fixture boom trace; the test outcome and subprocess exit are both zero. New LCOV is 145,123 bytes and matches the hosted LCOV hash below. New Codecov is 178,193 bytes, SHA256 `64d6e867d61f967ee455d54b65978ecbedda7879595a531166565ba04d497b58`; its byte hash differs from the hosted report. The parsed JSON reports differ in exactly one execution counter: `coverage/domain/ecs/parallel.clj/37` has 1,406 local hits versus 2,219 hosted hits. Both counts are positive; the exact comparison is retained without inferring its cause, a product defect, or changing source. Overall test outcomes and the LCOV report match, while the native and local Codecov reports retain separate hashes and provenance. All 300 prepared product files remain byte-identical, and the accepted worktree is tracked-clean. These are newly observed and retained bytes, not the former local run.

The new independent accepted-source store, worktree, JVM home, Maven repository, and caches are owned under the persistent recovery directory. Bubblewrap masks the user's home and root home, clears the environment, removes DISPLAY and credential environment variables, disables networking, exposes the host root and source read-only, and binds a private directory at literal `/tmp`. Only the private runtime home, temporary files, coverage target, and classpath cache are writable. The 768 MiB JVM heap and two active processors bound this preparation run; no services, GUI, real providers or shared cache writes are part of it.

The first sandbox preparation failed before execution because a new `/home/recovery` mount point could not be created below read-only `/home`. That failed output is retained. The corrected private `/home` mount passed the prerequisite checks and private classpath preparation. There was one whole coverage invocation after those prerequisites; the failed prerequisite is not a test result.

## Independently retained hosted outcome

[Upstream coverage run 37532420467](https://github.com/octave-commons/Truth/actions/runs/37532420467), attempt 2, and [coverage job 112557881933](https://github.com/octave-commons/Truth/actions/runs/37532420467/job/112557881933) are completed SUCCESS at exact accepted 58b0. Both the Coverage report and Upload coverage artifact steps succeeded. The newly fetched native log explicitly reports 879 tests containing 15,486 assertions, 0 failures and 0 errors, and ALL FILES line coverage 78.78% / form coverage 84.96%. Its raw bytes are retained in a lossless private base64 capture, 72,028 bytes, SHA256 `f65f264b5eabd77414cadf6d330b80c01c884503055a1b017b4ee0a309c713f3`.

Native artifact11451592786 named `coverage` was unexpired and retrieved from that actual run. Its LCOV report is 145,123 bytes / SHA256 `af0d3a4098b09597256e39982b1f663a3ae3def70b605c2ecc5761b1d4754eca`; its Codecov report is 178,193 bytes / SHA256 `25ac0099e5d4cb8366225ee2fe1a8484a70f7e73c63fe91abe97cadefa04b94f`. These hashes match the earlier recorded local report hashes, but the retained bytes have hosted retrieval provenance. They are not labeled as the lost local reports.

## Proposed admission decision

Retain the existing successful coverage workflow. Treat a failed or pending deterministic coverage execution as blocking wherever the applicable PR-flow admission requires that check; a successful review or another green job does not waive it. Continue publishing coverage percentages as measurements, without inventing an unreviewed numeric threshold.

Keep the distinction between process admission and GitHub protection explicit. Fresh native upstream main protection reports strict required context `analyze`, bound to App 15368, and the returned branch-rules list is empty. This slice proposes no repository-setting edit and does not assert that `coverage` is already a protected required context. Adding that required context, changing its trusted App binding, or selecting a numeric threshold would require a separately reviewed deliberate activation. The conscious decision in this evidence slice is to retain the workflow and its fail-closed process admission while preserving the existing protection configuration; it is a proposal for owner review, not silent demotion of an existing required check.

The original third criterion remains visible until this deliberate decision is reviewed and recorded through the native card operation. No checkbox or status is changed merely by writing this note. The failed, pending, skipped or incomplete review and publisher states of the sync and planning PRs remain separate blockers. Hosted coverage success is not full code-review qualification, source authentication, deployment evidence or authorization to merge.

## Recovery limits and native operation boundary

The former `/tmp` coverage candidate `5c5f98b21a076a00e256c7729e0fd585a7e5cb87` and publication audit `6125ad46acb2a39fa6344805ed2ecdd4d12debe0` are unavailable at their known owned paths and in the new store's fetched public reachable objects. The cause is unknown; no attribution to another agent or host change is established. The earlier local full-run stdout/stderr are not recreated.

The retained agent execution history records that the authorized coverage-card comment and ordinary successor had not been executed after the old 5c5 checkpoint. The recovered published card and current native read contain the unchanged original task; the public PR conversations contain no new coverage-admission handoff. Public absence does not prove facts about every unpublished store. No comment is repeated from authorization alone.

The exact lost ef3 compiled CLI artifact was not found at its known paths. A different actual installed owner CLI, `@eta-mu/rheos0.1.0` bundled with eta-mu1.1.1, passed help and read-task with the recovered exact configuration. Its hash is `fba8bef53a9dbc159136de56f5a3c174af33b0d309ae0a26fce440467b50590c`, 2,028,106 bytes. It is not asserted equivalent to the lost ef3 artifact and was used only for a read. The configured tasksDir remains `./kanban/tasks`, boardFile `.kanban/board.json`, and named FSM `promethean`. No fallback configuration, alternate parser, lifecycle transition or native comment is introduced.

## Verification and holds

The frozen recovery audit `b30fe86a0d11e89c078c4f9011248f2afbbe3c55` preserves 1,201 other published tree entries and both original receipt/reflection byte prefixes. Actual current Receipt River 154440 validates its single new declared observation at physical row 428, after the inherited 427 rows; no full legacy admission claim is made. Its 83 new base64 files consist of 78 command streams and 5 source-copy captures, not 83 independent observations. Six consumed consumer namespaces were verified; this does not claim examination of every owner source file.

This note is external, local preparation held for root peer review with the newly retained local result. No source push, PR creation, board comment, task transition, dependency repin, protection change or reviewer invitation is performed by drafting it. Hydro, dump isolation, sync qualification and the full simulation architecture remain separate scopes.
