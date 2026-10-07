#!/bin/bash
set -euo pipefail
out=${1:-"docs/demo/capture-$(date -u +%Y%m%dT%H%M%SZ)"}
if [[ -e "$out" ]]; then
  echo "Capture destination must be new: $out" >&2
  exit 2
fi
export TRUTH_DEMO_PORT=${TRUTH_DEMO_PORT:-7890}
if [[ ! "$TRUTH_DEMO_PORT" =~ ^[1-9][0-9]{0,4}$ ]] || (( TRUTH_DEMO_PORT > 65535 )); then
  echo "TRUTH_DEMO_PORT must be between 1 and 65535." >&2
  exit 2
fi
if ss -ltn "sport = :$TRUTH_DEMO_PORT" | tail -n +2 | rg -q .; then
  echo "Demo port $TRUTH_DEMO_PORT is occupied; choose a free TRUTH_DEMO_PORT." >&2
  exit 2
fi
mkdir -p "$out"
