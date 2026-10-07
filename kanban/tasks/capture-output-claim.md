---
uuid: "b1b46a3a-78e8-42a2-8a6b-17ff82d66cc5"
title: "Reserve native capture destinations for one owning run"
status: incoming
priority: P2
labels: "infra, capture, isolation, regression"
estimate: 3
design: "docs/designs/runtime-review-repair.md"
category: specs
---

# Reserve native capture destinations for one owning run

> Design: docs/designs/runtime-review-repair.md

## Context

Refs [Truth issue 31](https://github.com/octave-commons/Truth/issues/31), whose six acceptance criteria remain open. Planning merge does not close it.
CodeRabbit inline 4201617984 at exact Truth PR 2 at b239 identifies the separate existence check and mkdir -p in both capture adapters. The unchanged accepted58 preflight observer shows two fictional owners both exit0 for one private destination; existing-destination exits2 and separate destinations exit0. No actual media overwrite or host interference was executed. Current159 native card bodies,21 upstream PR bodies/files in the original snapshot (22 after the supplemental PR 30 read) and3 personal PR bodies show no exact reservation owner; foreign capture/evidence PRs remain distinct.

## Outcome
Exactly one run owns a destination before any recording, output-file write or capture process startup. A loser fails nonzero without overwriting/removing the winner's files. Both bin/demo-capture and bin/formation-capture retain their public capture contracts.

## Scope
An atomic filesystem reservation belongs to the existing Bash host adapters. Pure decision/shape contracts stay Clojure-shaped and .cljc when practical; no parallel filesystem domain or renderer. Preserve arguments, default names, duration/port/tool validation, unique scratch/diagnostic paths, exit cleanup and existing media/provenance names. Validate reservation ownership before effects; choose atomic directory creation or an equally strong reviewed claim, never check-then-mkdir-p. Nested parent handling must not weaken exclusive leaf ownership.

## Non-goals
No GUI/service/provider launch during planning; no renderer/media/encoding redesign, port allocator or second board engine, broad cleanup, shared environment changes, deployment or modification of foreign native evidence. Same-second default collisions fail closed; this proposal does not promise a capture will always succeed.

## Acceptance criteria
1. Two synchronized independent invocations of each adapter targeting the same previously absent private directory yield exactly one owner; the other returns a documented nonzero result before native process/media effects.
2. Existing file, directory and symlink destinations are refused without altering their contents, including dangling-link handling; caller-visible failure remains truthful.
3. Different destinations admit independent owners. A failed claim/validation never deletes or writes another owner's destination. Later owned cleanup preserves other runs and the existing process-cleanup contract.
4. Accepted options/default naming/duration/port/tool checks and output/provenance artifacts retain their existing meanings; report the first relevant failure, not a fabricated successful capture.
5. Hermetic actual-entry-point subprocess tests use private destinations and mocked outer executables, with synchronized race, loser-no-effects, existing-destination, independent-run and failing-claim controls. No production test flags or real renderer/provider contact.
6. Red new regression before green adapters; retain existing meaningful shell/demo tests, full configured Clojure suite and all six bin/analyze --strict gates. Native capture qualification remains separate from hermetic tests.

## Verification
See [the design](../../docs/designs/runtime-review-repair.md) and retained `.ημ/verification/runtime-review-repair-plan/race-observer-run.json` for the bounded preflight observation, not product-test qualification. These are first-class Incoming planning inputs, not admitted implementation work. Planning qualification, actual Rheos readiness, and a new isolated implementation branch are prerequisites. Frozen personal PR2 b239 and its historical review remain unchanged; no finding is settled by this proposal. Estimates are provisional; review must decide fit or lawful breakdown without dropping acceptance.


## Risks
Filesystem/symlink/nested-parent semantics and cross-script races must be explicit. Foreign branches change related native capture usage: implementation must serialize exact source seams with their owners, not adopt them or treat a green old base as current admission.
