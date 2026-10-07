---
uuid: "de9dc1ac-3084-48d0-862d-0c965c51c996"
title: "Make accepted-sync metadata verification fail closed and safe to retry"
status: "incoming"
priority: "P1"
estimate: 5
labels: "verification, provenance, regression, planning"
---

# Accepted-sync metadata review repair

## Context

This is authored Incoming planning input for review, not an operational transition or implementation admission. Personal Truth PR2 is frozen at `b239e6bf1a43fcce64ccebf9234b0a1ff0734be1`. CodeRabbit review 5435898183 identified four newly added verification-helper defects: comments 4201617962 (P2, receipt replay), 4201617970 (P2, capture timeout), 4201617975 (P1, addition allowlist), 4201617980 (P1, staged ledger prefix). The existing historical diagnostic files remain byte-preserved. Runtime demo/capture/HUD findings are separately coordinated and excluded here.

The archived `preservation-precommit.json` describes preparation, not a final committed-head verifier. Fresh independent Git-tree verification does preserve the published source identities and prefixes. Those facts do not make the helpers safe for future reuse: private unchanged-helper fixtures accepted an unapproved staged addition and a wrong staged ledger prefix after restoring its working-tree bytes; two successful unchanged receipt invocations appended the same fixed event ID twice. No duplicate published ledger or corrupt published commit is claimed.

## Outcome

A separately reviewed, explicitly superseding metadata verification path refuses unsupported additions and incorrect indexed provenance, admits safe identical receipt replay while refusing ID conflicts, and terminates subprocess capture within a documented finite bound while preserving partial outputs and failure evidence. Original preparation scripts/captures, accepted diagnostics, source ledgers and events remain inspectable; a new artifact clearly distinguishes predecessor preparation from current immutable-head/index verification.

## Scope

Only the four identified helper outcomes and their law/adapter tests, executable successor provenance and documentation. The proposed 5 points require planning review; split before implementation if concurrent receipt append or process-tree behavior exceeds the reviewed scope. Do not reduce any of the four outcomes to make the estimate fit.

## Non-goals

No simulation/demo/HUD/runtime capture changes; no rewriting inherited diagnostics or historical event/receipt rows; no source workflow/provider/config/protection/settings changes; no paid reviews, process/service control, host temporary cleanup or execution of arbitrary candidate commands with privileges; no replacement Rheos engine or alternate receipt parser; no claiming these repairs settle the independent review-body nitpicks or qualify the whole sync/review cohort.

## Acceptance criteria

1. **4201617975 — staged additions.** Obtain the full index path/mode/object/stage inventory through Git. Explicitly reject unmerged entries and every new path outside a documented, reviewed metadata allowlist. Preserve inherited non-append entries exactly. Positive permitted metadata additions pass; deliberate staged product/source additions, a misleading allowlist-prefix path, and disallowed modes fail. Do not claim a path-prefix inventory proves filesystem isolation or media review.
2. **4201617980 — staged versus working tree.** Compare the accepted ledger prefix to the actual staged blob and mode, separately from working-tree observations; retain explicit committed-head verification for publication. A valid working tree with a corrupted staged prefix must fail, as must an invalid mode or missing entry. Valid staged append passes. Historical prefix bytes are never rewritten. A final immutable-head proof independently binds complete paths/modes/object IDs and both append prefixes without relying on the precommit helper's claim.
3. **4201617962 — receipt replay/deduplication.** Use owning Receipt River/Clio APIs for event admission and existing ledger read semantics. Freeze the intended receipt envelope/content before append; retrying the same admitted event leaves exactly one row and preserves every prior byte. The same event ID with conflicting content refuses before append; malformed prior input is surfaced, not silently skipped. Explicitly review concurrent retry behavior: if concurrent invocations are supported, demonstrate one append or fail closed under the owning adapter's lock/atomic contract; otherwise expose and enforce a single-writer restriction instead of claiming concurrency safety. Do not regenerate timestamps on retry, blindly skip by ID, introduce a second parser, or amend historical rows.
4. **4201617970 — bounded capture.** Give each captured subprocess a finite documented deadline. Preserve available partial stdout/stderr as exact bytes with sizes/hashes and truthful timeout/nonzero metadata. Refuse to describe timeout as ordinary success. Cover normal success, normal failure, timeout with partial output, and any declared child cleanup semantics using private fixture commands. Cleanup may affect only owned fixture children; no global process kill. Every current supported invocation must have a finite bound; archived original commands remain unchanged provenance.
5. Keep decision/shape/preservation laws portable `.cljc` where practical; Git, file append, process/timer and locking effects remain outer adapters. Existing owning event admission and policy are consumed rather than replaced. A Python/CLJS predecessor archive is not itself authority for an alternate portable event contract.
6. Red/green fixtures expose the specific wrong acceptance/replay/hang contracts before implementation and pass afterward. Run appropriate actual consumer, focused adapter checks, and all six existing `bin/analyze --strict` tiers plus required hosted checks for any eventual product candidate. JVM/CLJS portable-law checks are future acceptance, not claimed current passes. Record exact current heads and scopes; no approval, lawful ready state, or completed review round follows from this draft.

## Verification

Fresh evidence is retained in the scoped verification packet with decoded-byte hashes and native/source provenance. Five bounded private fixture invocations completed exit 0: valid-baseline, unapproved-addition accepted, wrong-staged-prefix accepted, first receipt append, repeated receipt append. This is RED contract evidence about unchanged helpers; the initial helper's exit 0 on negative inputs is the observed defect, not a passing regression suite. Timeout is source-confirmed from an unbounded `subprocess.run` and eleven archived commands without outer timeout; no deliberately hanging process was launched.

## Risks

A reusable validator must distinguish indexed/committed facts from working-tree state and original one-shot preparation scope. ID-only dedup hides conflicts; timestamp regeneration defeats equality. Process termination/partial-output behavior and concurrent append need explicit edge contracts before implementation. Native authenticated settlement remains separately required: this plan alone does not clear any original thread or historical diagnostic nitpick. Planning convergence and a lawful Rheos ready transition precede implementation.

## Current helper authority versus immutable observations

A future qualified ordinary repair may change the current owned helper code. Preserve the original helper implementation through its immutable b239 Git blob/commit (or a lossless clearly inactive archive), and preserve the original observations/captures and historical ledger bytes exactly. There must be one named active verifier path, with an explicit current contract and version/base/head, not two competing active authorities. Calling the old code an archive is insufficient if callers can still invoke it as the current validator. The actual staged-index path/mode/OID set and exact reviewed allowed-addition policy, plus indexed prefix negative controls, are substantive acceptance requirements. Documentation-only relabelling does not fix the reproduced defects.
