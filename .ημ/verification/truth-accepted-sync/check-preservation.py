import subprocess,json,hashlib,base64
from pathlib import Path
r=Path(__file__).parent;w=r/'worktree';b=json.loads((r/'accepted-baseline-proof.json').read_text()); base=b['accepted_tip']
def git(*args):return subprocess.check_output(['git','-C',str(w),*args])
def sha(raw):return hashlib.sha256(raw).hexdigest()
entries=b['all_tree_OID_inventory']; changes={'receipts.edn','.ημ/session-mycology/ledger.md'}
indexed={}
for line in git('ls-files','--stage','-z').split(b'\0'):
 if not line: continue
 header,path=line.split(b'\t',1);mode,oid,stage=header.decode().split();assert stage=='0';indexed[path.decode()]={'mode':mode,'oid':oid}
unchanged=[]
for e in entries:
 p=e['path'];assert p in indexed
 if p not in changes:
  assert indexed[p]['oid']==e['oid'] and indexed[p]['mode']==e['mode'],p;unchanged.append(p)
prefixes=[]
for p in sorted(changes):
 old=git('show',base+':'+p);new=(w/p).read_bytes();assert new.startswith(old)
 prefixes.append({'path':p,'old_bytes':len(old),'old_sha256':sha(old),'new_bytes':len(new),'new_sha256':sha(new),'prefix_exact':True})
manifest=json.loads((r/'capture-manifest.json').read_text())
for e in manifest:
 for channel in ['stdout','stderr']:
  meta=e[channel];raw=base64.b64decode((r/meta['capture']).read_bytes(),validate=True);assert len(raw)==meta['bytes'] and sha(raw)==meta['sha256']
for revision in [b['personal_base'],*b['accepted_commits']]:assert subprocess.run(['git','-C',str(w),'merge-base','--is-ancestor',revision,'HEAD']).returncode==0
proof={'base':base,'personal_base':b['personal_base'],'existing_entries':len(entries),'unchanged_existing_entries':len(unchanged),'append_only_existing_paths':prefixes,'accepted_8_commits_ancestor':True,'engine_events_exact':[p for p in unchanged if '/.events/' in p],'all_cards_configs_code_workflows_accepted_OIDs_exact':True,'gitlinks_exact':[e for e in entries if e['mode']=='160000'],'commands':len(manifest),'strict_streams':len(manifest)*2,'captured_failed_commands':[e['label'] for e in manifest if e['exit']!=0],'no_full_suite_qualification':True,'sparse_object_identity_not_content_review':True}
(r/'preservation-precommit.json').write_text(json.dumps(proof,indent=2)+'\n');print(json.dumps(proof))
