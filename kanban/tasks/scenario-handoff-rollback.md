---
uuid: "35d70711-409d-481c-9aee-496e7694da73"
title: "Restore scenario handoff pause settings on failure"
status: incoming
priority: P2
labels: "infra, demo, regression"
estimate: 2
design: "docs/designs/runtime-review-repair.md"
category: specs
---

# Restore scenario handoff pause settings on failure

> Design: docs/designs/runtime-review-repair.md

## Context
CodeRabbit inline 4201617989 at exact b239 identifies select! pause settings left at identity when world replacement fails. Exact accepted function-form observers show failed reads and interruption preserve the original exception but leave tick-fn/on-step paused across present/nil/absent prior-key cases. Existing prepare-formation! restores those settings and unrelated concurrent fields; success controls complete for both. Accepted dev/demo_test.clj already tests formation rollback; no matching select! outcome found in current native cards/PR scopes.

## Outcome
Failed queued scenario handoff restores the previous tick-fn and on-step key presence and values, propagates the original failure, and preserves independent config changes. Successful scenario and formation behavior retains its accepted semantics.

## Scope
Review a reusable pure .cljc restoration decision when practical and keep atoms/intent queue/wait/camera as outer adapters. Cover queue submission, dereference, timeout and interrupted wait after pause. Do not restore the entire prior config map or roll back another writer. Reuse the proven prepare-formation! policy without changing its successful paused-formation contract. Preserve scenario validation, ECS single-writer publication and the selected scenario/camera result.

## Non-goals
No second world writer, scenario fixtures promoted to natural gameplay, real nREPL/window/provider operations, new overlapping-select protocol, generic transaction framework or foreign flight/camera changes. Late intent acceptance after a failed wait is an explicit existing-contract review question, not a claim that this plan cancels all queued work.

## Acceptance criteria
1. Failed world read, queue-submission failure, timeout and interruption restore exactly tick-fn/on-step presence and values, including nil and originally absent keys; the original failure remains observable.
2. Unrelated config edits during the failed handoff survive. Missing service/invalid scenario failures occur before pause mutation and preserve config/camera.
3. Successful select! continues the selected scenario/tick policy, framing/result and ECS-owned publication; successful prepare-formation! remains intentionally paused with its existing framing.
4. Retain and extend actual dev/demo_test.clj formation regressions with select! cases, failed-boundary controls and successful controls. Use bounded injected boundaries/private state, not real services, skipped timeouts or rewritten test expectations.
5. Review late queued-intent behavior and existing concurrency contract explicitly; do not silently clear another writer's work or add overlapping-send semantics to satisfy tests. An independently necessary outcome requires a reviewed breakdown.
6. Red new regressions before green; retain the complete configured test suite and all six strict-analysis gates. Planning observers are evidence of the defect, not implementation qualification.

## Verification
See [the design](../../docs/designs/runtime-review-repair.md) and `.ημ/verification/runtime-review-repair-plan/function-observer-run.json`. These are first-class Incoming planning inputs, not admitted implementation work. Planning qualification, actual Rheos readiness, and a new isolated implementation branch are prerequisites. Frozen personal PR2 b239 and its historical review remain unchanged; no finding is settled by this proposal. Estimates are provisional; review must decide fit or lawful breakdown without dropping acceptance.


## Risks
Restoration itself may encounter a failed adapter; preserve the primary failure and make secondary failure observable without claiming recovery succeeded. Successful camera operations and world-acceptance failures are distinct contracts and must not be conflated.
