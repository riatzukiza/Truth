---
category: "specs"
labels: ["specs", "ci", "tooling", "testing"]
write-id: "1791335352659-0.123fwmw0fo1gyrt2x31"
source: "kanban/tasks/ci-coverage-workflow-broken.md"
title: "The `coverage` CI check has been failing on a missing cloverage dependency"
priority: "P2"
status: "ready"
estimate: "2"
uuid: "ci-coverage-workflow-broken"
created_at: "2026-07-25T00:00:00Z"
---

# `coverage` CI fails: `ClassNotFoundException: TablesExtension`

Found 2026-07-25 while landing
`kanban/tasks/static-analysis-regression-2026-07-24.md`. Not caused by that work —
it fails identically on `main` and on `spark-gravity-bound-body`.

## The failure

`.github/workflows/coverage.yml` → "Coverage report" step:

```
Execution error (ClassNotFoundException) at java.net.URLClassLoader/findClass
  (URLClassLoader.java:445).
org.commonmark.ext.gfm.tables.TablesExtension
:cause "org.commonmark.ext.gfm.tables.TablesExtension"
```

A missing transitive dependency, not a coverage threshold. Cloverage's Markdown
reporter wants `com.atlassian.commonmark/commonmark-ext-gfm-tables` (or the newer
`org.commonmark/commonmark-ext-gfm-tables`) on the classpath and it is not in the
`:coverage` alias.

## Why it matters

`coverage` is **not** a required status check, so it does not block merges — which
is precisely why it has been able to sit red. Same shape as the failure the
static-analysis epic just closed: a check that runs, fails, and is ignored.

Either fix it or delete the workflow. A permanently-red non-required check trains
everyone to ignore the checks list, which is what let `static-analysis` stay red
from 2026-07-10 to 2026-07-21.

## Likely fix

Add the missing artifact to the `:coverage` alias in `deps.edn`, or pin cloverage
to a version whose reporter deps resolve. Reproduce locally first:

```bash
bin/coverage --text
```

Check whether it fails the same way locally — if it does, this is purely a deps
problem and nothing to do with the CI environment.

## Done when

- [ ] `bin/coverage --text` succeeds locally.
- [ ] The `coverage` workflow is green on `main`, or the workflow is deleted with a
      note saying why.
- [ ] If it is kept and made green, decide deliberately whether it should become a
      required check (see `docs/STATIC-ANALYSIS.md` › The gating contract for the
      argument that a non-gating check decays).

---
Fresh coverage evidence and proposed admission decision; no task transition or checklist edit.

The original three completion criteria remain in full: local bin/coverage --text succeeds; hosted coverage is green on main or deliberately deleted; if retained, decide whether coverage becomes a required check.

A NEW unchanged whole bin/coverage --text run at accepted 58b0fd91ea65b7a4b3a1f571f22f3d394374a871 exited 0: 879 tests / 15,486 assertions / 0 failures / 0 errors; line coverage 78.78%, form coverage 84.96%. Its full stdout/stderr and reports are retained in a new persistent private audit. The subprocess used network-disabled bubblewrap, read-only source/host root, private literal /tmp, isolated home/JDK/Maven/cache, no DISPLAY or credential environment. No filters or source/test edits were made; all 300 prepared product files remain exact.

Accepted-main coverage run 37532420467 attempt 2 / job 112557881933 also completed SUCCESS with report and upload steps successful. Its native raw log and artifact 11451592786 are freshly retained. LCOV matches the new local report. Codecov has one different positive hit counter (domain/ecs/parallel.clj line 37: local 1,406 / hosted 2,219); separate hashes and the complete comparison remain visible.

Proposed deliberate decision: retain the successful workflow and fail closed on deterministic execution failures under applicable PR-flow admission; keep percentages as reporting without introducing an unreviewed threshold. Current strict upstream protection requires analyze (App 15368); this evidence slice changes no settings and does not claim coverage already is a protected required context. Adding that context or a numeric threshold requires separate reviewed activation. The original third criterion remains visible for owner review of this explicit proposal.

The formerly observed local run and unpublished 5c5 candidate remain unavailable; these are NEW local and newly retrieved HOSTED observations, not reconstructed old bytes. No previous authorized comment had executed in this agent's retained history, and no old event is invented. READY/estimate 2/all original task text remain unchanged. Sync, dump isolation, hydro and review qualification remain separate. This evidence comment does not transition the task or mark its checklist complete.
---