import sys,os,subprocess,json,hashlib,base64,datetime,fcntl
from pathlib import Path
p=Path(__file__).parent
label=sys.argv[1]; command=sys.argv[2:]
started=datetime.datetime.now(datetime.timezone.utc).isoformat()
r=subprocess.run(command,capture_output=True,env=os.environ.copy())
entry={"label":label,"command":command,"cwd":os.getcwd(),"at":started,"exit":r.returncode}
for name,data in [("stdout",r.stdout),("stderr",r.stderr)]:
 path=p/(label+"."+name+".b64"); path.write_bytes(base64.b64encode(data)); entry[name]={"capture":path.name,"bytes":len(data),"sha256":hashlib.sha256(data).hexdigest()}
manifest=p/"capture-manifest.json"
with (p/"capture-manifest.lock").open("w") as lock:
 fcntl.flock(lock,fcntl.LOCK_EX)
 records=json.loads(manifest.read_text()); records.append(entry); manifest.write_text(json.dumps(records,indent=2)+"\n")
print(json.dumps(entry))
if r.returncode: print(r.stderr.decode(errors="replace")[-2400:])
sys.exit(r.returncode)
