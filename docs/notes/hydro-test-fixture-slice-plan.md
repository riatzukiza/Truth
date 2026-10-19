# Three-site hydro fixture planning contract

This is planning, not implementation. Source baseline is
`b76e87381c3ed39c66059236bb14843cecc85ff7`; personal `riatzukiza/Truth` and
upstream `octave-commons/Truth` default main were identical at preparation.
The existing `static-analysis-jscpd-test-fixtures` ready card stays open at five
points. The new incoming three-point story covers only three source-confirmed
setup sites, with the predecessor identity referenced in the body rather than
an unverified parent/dependency field.

The proposed constructor belongs in `test/support/worlds.clj`. It uses the
existing sequential `seeder/spawn-clump` calls on an empty ECS world, with these
facts for both entities: velocity `[0.0 0.0 0.0]`, mass `1e28`, radius `1e14`,
`:matter-state :nebula`, temperature `12.0`. First position is `[0.0 0.0 0.0]`,
second `[1e14 0.0 0.0]`. Its proposed result is a map with the resulting world,
first entity id and second entity id. Derived density/radius/component values
remain those supplied by the actual seeder; no invented replacement world model
or component defaults are introduced. The constructor performs no index, system
execution or write-set fold.

| Named test | Confirmed setup lines at baseline | Observation retained |
| --- | --- | --- |
| `test-density-system-updates-nebula-density` | 265–278 | Density becomes positive and exceeds seed density |
| `test-density-pressure-consistent` | 304–317 | Pressure matches ideal-gas law of the new density at 12 K |
| `test-density-system-updates-radius` | 327–340 | Crowding reduces radius from its seeded value |

Caller-local spatial-index computation stays after construction, in its original
order. Existing test names, assertion expressions and assertions remain intact.
New focused boundary laws must discriminate altered position, mass or temperature
against independent expected facts, with red/green evidence and any new assertions
reported separately. Avoid configurable catch-all fixtures: these three sites
need the same closed setup. Other hydro cases have different positions, radii,
states or temperatures and remain unchanged.

`test/architecture_test.clj` guards production layering and single-ECS semantics.
`test/test_runner.clj` discovers every `.clj` namespace except `test-runner`;
all/fast includes `support.worlds` although it defines no `deftest`, while domain
selection excludes it. Cognitect uses its own test-namespace pattern. Measure
both existing selection paths before/after; do not assert that every runner ignores
helper namespaces. No runner or production architecture change is proposed.

Current `.jscpd.json` has threshold `1.7` and explicitly preserves independent
Lorentz-versus-MHD construction because sharing would weaken their equivalence
proof. Leave those tests unchanged. Condensation setup is a separate future slice:
its mass/density/temperature, position, disk membership, one-shot marker, clock
and spatial-index variations require their own contract. It is outside this PR.

Raw audit: [manifest and hashes](../../.ημ/verification/hydro-fixtures-20261006/manifest.json),
[JSON](../../.ημ/verification/hydro-fixtures-20261006/jscpd-report.raw.json),
[stdout](../../.ημ/verification/hydro-fixtures-20261006/jscpd.raw.out),
[actual command](../../.ημ/verification/hydro-fixtures-20261006/command.raw.json).
The current owner gate selects `jscpd@4`; the observed executable was 4.3.0.
Use that exact version for future before/after evidence. The run reported 275 files,
49,602 lines, 65 clones, 800 duplicated lines (1.61%) and exited zero. Seven
hydro-self/six condensation-self records are tool output, not thirteen verified
semantic duplicates. Raw JSON contains hydro `secondStart` 505 / `secondEnd` 279,
and condensation `secondStart` 41 / `secondEnd` 108 paired with a 19-line span.
Preserve those raw inconsistencies; the three selected sites rest on direct source
inspection. Do not promise a reduction for every reported clone or completion of
the predecessor's broader approximately 20-clone/0.5% target.

Verification for future implementation: focused hydro/condensation/focus and
architecture suites with before/after selection and test/assertion counts; strict
`bin/analyze --strict`; full source-config jscpd with exact 4.3.0 and private cache,
temporary paths/output; current-head hosted checks. No JVM tests or full gate were
executed by this planning preparation. Prerequisites are native planning convergence
and an actual Rheos ready event for the new story. Existing ready status is preserved
and historical event absence does not establish illegality. Truth's current
Promethean configuration and owner commands require a verified lawful gate path
when implementation advances; this plan supplies no alternate board engine or gate.

Foreign Truth PR7 at preparation head `c35cd65e40cd5e0da1930cf7ac5b183606dbf5ab`
is owned by the active “Run Truth and capture a GitHub demo” lane. Its demo/gallery,
AGENTS/CLAUDE/README, dependencies, launcher, HUD and runtime are untouched.
No inherited eager merge caller was found in the six baseline workflow files;
repository auto-merge is disabled. Ready PR publication permits review, not
implementation, merge, staging or production activation.
