---
uuid: "6e7e3556-a697-4005-b8e4-11930791046e"
title: "Isolate error-dump bundles and cleanup by owning run"
status: "incoming"
priority: "P1"
points: 3
labels: planning, regression, parallel-isolation, infra
created_at: "2026-10-06"
source: "kanban/tasks/error-dump-run-isolation.md"
category: "specs"
design: "docs/designs/error-dump-run-isolation.md"
---

# Isolate error-dump bundles and cleanup by owning run

> Design: [Error-dump run isolation](../../docs/designs/error-dump-run-isolation.md)
> Native issue: [Truth #26](https://github.com/octave-commons/Truth/issues/26).

## Context

Accepted source `58b0fd91ea65b7a4b3a1f571f22f3d394374a871` uses a global literal `/tmp/gates-of-truth/dumps` and tick/millisecond names. The test fixture deletes every `truth-error-*` file there. A safe unchanged-source observation inside a private filesystem created two fictional owners' three-file bundles and showed one owner's cleanup deleting all six; an unrelated-prefix control survived. No real host interference or timestamp collision was observed.

The native board intake captured 159 full card bodies and the initial 17 upstream PR bodies/file scopes. No matching error-dump ownership contract was identified there. The coordinator subsequently reviewed newly observed foreign PR25 (head `4805fae52bd6764e4cf01555bc1d7417dd373349`), which concerns hardware/native evidence and does not add that contract; its fresh inventory had 18 open PRs. Generic cleanup cards and PR15's GL teardown concern different resources; PR19 changes focus cadence in the same loop file. These are coordination references, not UUID hard dependencies or permission to adopt their work.

## Outcome

Each runtime/test run owns its dump storage and cleanup. Distinct runs and distinct dumps retain separate inspectable world/ledger/metadata evidence, including when tick and wall-clock time coincide.

## Scope

- Explicit run ownership, safe context/root validation and unique per-dump identity.
- Both the production writer and test cleanup contract, retaining all three bundle members and observable failure/log fields.
- Portable `.cljc` laws for Clojure-shaped ownership/identity/path relationships where practical; filesystem, clock, ID allocation and lifecycle remain JVM adapters.
- Meaningful paired-context/concurrent/negative-control regressions and the original required gates.

## Non-goals

No physics or focus-cadence change, UI redesign, provider/model work, running-service adoption or restart, historical-dump purge, test filtering, dependency/coverage-workflow repair, global configuration or alternate board/event engine. Container isolation is observation containment, not the proposed product repair.

## Acceptance criteria

1. An explicit owned context separates independent runtime/test runs, including two contexts in one JVM. Validate ownership before any filesystem effect.
2. Unique per-dump identity prevents overwrite for same-tick/same-millisecond calls; bundle metadata identifies its run and dump without treating time as identity.
3. Cleanup removes only its context's exact owned bundle paths. Other matching-prefix bundles, unrelated controls and old unscoped dumps survive; invalid/traversing/symlink-substituted ownership cannot escape the root.
4. Preserve world, ledger and metadata snapshots, returned `:world-path`/`:ledger-path`/`:meta-path`, logged paths/tick and graceful `{:error ...}` behavior. Failure preserves other owners' files; explicit review covers any interface change.
5. Paired/reset/concurrent fixtures and collision/invalid-input/write-failure controls fail for the current ownership fault and pass after repair. Retain existing assertions and real nonzero failure behavior, without suppressing cleanup or selecting away namespaces.
6. Full `clojure -M:test`, all six `bin/analyze --strict` tiers and isolated unchanged `bin/coverage --text` pass with measured scope/counts and preserved history. These local results do not imply native review convergence, lawful readiness or completion of the separate coverage card.

## Verification

Grounding is the unchanged-source contained observation described in the design. Future RED must demonstrate the current cross-owner deletion and same-time path identity defect; GREEN implements pure contracts before adapters. Use fictional per-run temporary storage and supplied clock/ID values at replaceable boundaries. Run the real full suite and original strict gates; record commands, exit codes, selected namespaces, test/assertion counts and source hashes. Do not manufacture a passing coverage result from classpath preparation.

The existing `ci-coverage-workflow-broken` card remains READY with estimate two and all three original outcomes. This new Incoming three-point proposal does not change that card, its status or its dependency-resolution investigation.

## Risks and readiness

The three-point estimate is proposed for review. If compatibility, lifecycle or filesystem scope exceeds it, reviewers must require lawful breakdown; no outcome may be dropped merely to fit the estimate. Implementation must coordinate foreign PR19's loop-file edits and preserve PR15's separate runtime/dependency scope. Native planning convergence and a lawful Rheos ready transition precede code. Personal publication requires the independently reviewed accepted-upstream synchronization base; no direct main update, forced history or deployment admission is implied.
