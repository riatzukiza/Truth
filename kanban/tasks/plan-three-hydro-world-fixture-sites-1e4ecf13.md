---
uuid: "1e4ecf13-4768-4bc0-b591-42abbfcf6a92"
title: "Plan shared setup for three hydro density tests"
status: incoming
type: story
priority: P3
points: 3
estimate: 3
labels: static-analysis, testing, jscpd, planning
category: specs
source: "kanban/tasks/plan-three-hydro-world-fixture-sites-1e4ecf13.md"
created_at: "2026-10-06T16:12:25.761400Z"
---

# Plan shared setup for three hydro density tests

## Context

The existing canonical card `static-analysis-jscpd-test-fixtures` remains ready,
open, and estimated at five points. It is the predecessor backlog for this
proposed subslice; this body reference does not assert a UUID parent or hard
dependency. Its state, body and event history are preserved.

At source `b76e87381c3ed39c66059236bb14843cecc85ff7`, the same two-nebula
construction appears in `test/domain/hydro_test.clj` at lines 265–278, 304–317,
and 327–340. These are the density-update, pressure-consistency and radius-update
tests. The [design](../../docs/notes/hydro-test-fixture-slice-plan.md) records
source values, ownership boundaries, runner behavior and raw clone evidence.

## Outcome

After qualified planning and Rheos readiness, one pure test-world constructor
can serve exactly these three setup sites without changing their observations.
This incoming story owns three points of bounded future work; it does not close
or re-estimate the five-point predecessor or claim the whole clone target.

## Scope

- Add one test-only constructor to `test/support/worlds.clj`.
- Replace setup only in `test-density-system-updates-nebula-density`,
  `test-density-pressure-consistent`, and `test-density-system-updates-radius`.
- Preserve sequential spawn identity, component values, spatial-index timing,
  system invocations, assertion meaning, test names and assertion counts.

## Non-goals

Condensation, other hydro setups, Lorentz/MHD equivalence construction, production
physics, thresholds/exclusions, runner changes, demo/gallery, HUD, run aliases,
AGENTS, dependencies, workflows, services and process interaction are outside
this story. The remaining whole-card backlog stays open.

## Acceptance criteria

- [ ] The constructor builds two distinct entities in the same sequential order,
      positions `[0.0 0.0 0.0]` and `[1e14 0.0 0.0]`, zero velocity, mass `1e28`,
      radius `1e14`, state `:nebula`, temperature `12.0`, using the existing
      `seeder/spawn-clump` behavior. Return the world and stable entity references.
- [ ] Construction does not run a system, spatial-index computation or write-set
      fold. Each caller keeps its original spatial-index boundary and observations.
- [ ] Exactly the three named tests use the fixture. Their independent assertions
      remain unchanged; no Lorentz/MHD test or production file changes.
- [ ] Focused red evidence fails for the absent constructor; green restores the
      existing density, pressure and radius assertions. Meaningful fixture-boundary
      checks catch perturbed mass, position or temperature using independent
      expected component facts, rather than asserting the helper against itself.
- [ ] Focused hydro, condensation, focus and architecture suites pass with recorded
      exact revision, commands, test/assertion counts and runner selection. Existing
      tests/assertions are preserved; any new fixture-law assertions are identified.
- [ ] The full strict gate passes and whole-tree jscpd runs with the same exact
      `4.3.0` tool/config. Source-confirmed target duplication decreases; unrelated
      remaining clones and diagnostic coordinate caveats stay visible. Never weaken
      the `1.7` threshold or add clone exclusions to satisfy this story.
- [ ] Native planning review converges and Rheos records this story ready before
      implementation. Neither predecessor status nor PR merge implies that event.

## Verification

Observed audit only: jscpd 4.3.0 exited zero, 275 files, 65 reported clones,
800 duplicated lines / 1.61%. Raw JSON, stdout, actual command and SHA-256 hashes
are committed under [.ημ verification](../../.ημ/verification/hydro-fixtures-20261006/manifest.json).
Seven hydro-self and six condensation-self report records are diagnostic counts;
two ranges have inconsistent coordinates. The three setup sites above were
independently confirmed in source. No JVM suite or full gate ran in this plan.

Future implementation retains focused before/after counts, grouped discovery,
strict gate and exact-head hosted checks. Full test counts are measured at the
implementation revision, not copied from historical 879-test documentation.

## Risks

A fixture can conceal distinct preconditions or make an equivalence test circular.
The tiny closed scope, independent expected facts and untouched Lorentz/MHD tests
prevent that scope expansion. Current grouped discovery includes non-test CLJ
namespaces for all/fast; a helper-only namespace must add no test var or runner change.
Rheos status is authoritative; review qualification and actual gate execution remain
separate evidence. No automatic merge or deployment is activated by this plan.
