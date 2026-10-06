# Error-dump run isolation — proposed contract

This proposal addresses both the production writer's bundle identity/storage and the fixture's deletion ownership. It is planning grounded in accepted source, not an accepted implementation choice or a readiness claim.

## Grounding and observed boundary

At accepted `58b0fd91ea65b7a4b3a1f571f22f3d394374a871`, `src/infra/dev/window/loop.clj` uses literal `/tmp/gates-of-truth/dumps`. Its private writer formats a base from tick and `System/currentTimeMillis`, writes world/ledger/metadata EDN, returns three paths and reports failures as `{:error ...}`. `test/infra/dev/window_test.clj` registers an `:each` fixture that deletes every `truth-error-*` member of that shared directory.

The preserved independent observer loads unchanged source and invokes the actual writer for two fictional logical contexts, then the actual fixture with a no-op body. Inside an owned Bubblewrap filesystem, both three-file bundles exist before cleanup and all six disappear afterwards; a nonmatching-prefix file survives. The host root is read-only, `/tmp` is privately mounted, network is unshared and there are no real host inputs. This is a causal observation of cleanup scope, not a software-suite result, two-process concurrency result or executed timestamp-collision proof. Independent source/transport review confirmed the observation.

Tick plus milliseconds alone also permits two calls to target the same base. That is a static naming-risk finding. Future regression must exercise equal supplied time/tick with distinct dump identities; the current observation does not claim that collision occurred.

Project grounding: root `AGENTS.md` single-substrate/effect boundary and required analysis contract; `PROCESS.md` research/design/task traceability and readiness; the original writer/fixture's existing snapshot/log/error assertions. This is an infrastructure contract repair; it adds no simulation world or physics law.

## Semantic and adapter boundary

Represent run ownership, dump identity and bundle members as ordinary Clojure data. Proposed pure `.cljc` shape/law functions validate nonempty ownership, the run→dump relationship and safe relative member names. They decide the intended world/ledger/metadata bundle and admissible cleanup membership; they do not create directories, read JVM state, inspect symlinks, allocate IDs, consult wall time or delete files.

Outer JVM adapters allocate a runtime/test context and a distinct opaque dump identifier, anchor paths beneath that context, check the actual filesystem containment/symlink boundary, perform writes and delete only owned members. A clock may supply metadata but cannot be the uniqueness authority. Avoid a global mutable ownership table, prefix guesswork, a generic helper namespace, or a new event ledger.

Two contexts in one process must be independent, so a single process-global temporary directory is insufficient. The lifecycle/context seam and default compatibility path require review: preserve existing caller semantics through a lawful adapter, or make the intentional interface change explicit with updated consumers and tests. Do not silently reuse a legacy global directory when ownership is absent. Historical unscoped dumps remain evidence, never cleanup candidates.

## Complete repair outcome

1. Explicit runtime and fixture contexts own distinct roots and exact bundle membership.
2. Distinct dump identities survive equal tick/time without overwrite. Metadata links each dump to its owning context and correct member paths.
3. Context-scoped cleanup leaves other owners, nonmatching files and legacy unscoped evidence unchanged. Path traversal, root substitution and symlinks cannot redirect deletion into another owner's storage; invalid inputs fail before effects.
4. World and ledger snapshots, metadata, returned path keys, frame-error logs and graceful failure shape remain meaningful. Write/cleanup failures do not erase unrelated evidence. Partial owned output must remain inspectable with truthful failure reporting.
5. Paired contexts, reset/teardown, concurrent independent operations, same-time identity controls, malformed ownership, containment escapes and write failures receive meaningful RED/GREEN tests. A deliberate failure must make the owning runner exit nonzero; do not suppress the original fixture or filter tests.
6. The original whole test suite and six strict analysis tiers remain required. Execute the unchanged full coverage command with private storage, disclose real scope/counts, preserve all original assertions and record source fidelity.

## Test and delivery plan

First add ownership/identity laws and regressions that fail against the accepted behavior. Use supplied identity/time data at pure boundaries and actual filesystem adapters in fictional temporary contexts. A paired test must preserve B's complete world/ledger/metadata bytes when A is cleaned. A same-time control must retain both distinct bundles. Negative controls include an unrelated member, a preexisting unscoped bundle, empty/malformed ownership, traversal/symlink substitution and a failing write.

Then implement the pure contract and effect adapters. Verify the lifecycle reset policy and concurrent ownership independently, without services/providers or test-only production bypass flags. Preserve the existing graceful failure and logged-path tests; their constants may describe fictional paths but cannot stand in for actual ownership isolation.

Run `clojure -M:test`, `bin/analyze --strict` (clj-kondo, Splint, clojure-lsp, cljfmt, structural HARD and jscpd), and unchanged `bin/coverage --text`. Record observed namespace/test/assertion counts and exits, including failed preparation. Use replaceable boundary tests and mutation evidence when required by the touched contract; no weakened ratchet or advisory conversion. A local gate pass does not confer hosted review/merge authority.

## Coordination and limits

Foreign PR19 edits focus cadence in the loop namespace. Future implementation must serialize/coordinate shared paths and carry its accepted semantics forward; this body does not adopt that PR or invent a UUID hard dependency. PR15's native GL and dependency work remains separately owned. No provider calls, service restarts, model execution, deployment, physics or camera behavior is part of the repair.

The existing READY2 coverage card retains local success, actual hosted-main success (or reasoned deletion) and the explicit required-check decision. The previously failed hosted dependency resolution is investigated separately; the unchanged whole command now has a contained local pass, and the coordinator-owned hosted retry attempt2 has native success at the same accepted head (run37532420467/job112557881933). These are separate coverage observations, not qualification of this proposed repair. This plan does not change that card or infer qualified planning from its historic status.

The new Incoming3 story is a complete outcome proposal. Review decides whether three points fits; lawful breakdown is required if it does not, rather than silently narrowing to cleanup alone. Native issue [Truth #26](https://github.com/octave-commons/Truth/issues/26) is coordinator-published. The coordinator supplementally reviewed PR25 at `4805fae52bd6764e4cf01555bc1d7417dd373349`; its hardware/native evidence scope does not cover this contract. Personal synchronization/planning publication remains coordinator-owned and pending. Preserve historical source/card/events/receipts; no implementation or transition has occurred.
