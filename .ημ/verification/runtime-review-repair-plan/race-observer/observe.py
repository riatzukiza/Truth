import subprocess,pathlib,os,json,hashlib
p=pathlib.Path(__file__).parent
rows=[]
for name in ['demo-capture','formation-capture']:
 d=p/'runs'/name; d.mkdir(); sync=d/'barrier'; sync.mkdir()
 command=['/bin/bash',str(p/(name+'-preflight.sh'))]
 processes=[]
 for owner in ['A','B']:
  env=os.environ.copy(); env.update(PATH=str(p/'shims')+':/usr/bin:/bin',OBS_ID=owner,OBS_SYNC=str(sync),OBS_BARRIER='1',TRUTH_DEMO_PORT='7890' if owner=='A' else '7891')
  processes.append((owner,subprocess.Popen(command+[str(d/'shared-destination')],env=env,stdout=subprocess.PIPE,stderr=subprocess.PIPE)))
 exits=[]
 for owner,proc in processes:
  out,err=proc.communicate(timeout=10); exits.append(proc.returncode)
  (d/(owner+'.stdout')).write_bytes(out); (d/(owner+'.stderr')).write_bytes(err)
 assert exits==[0,0],exits
 env=os.environ.copy(); env.update(PATH=str(p/'shims')+':/usr/bin:/bin',OBS_BARRIER='0')
 old=subprocess.run(command+[str(d/'shared-destination')],env=env,capture_output=True)
 assert old.returncode==2,old.returncode
 separate=[]
 for owner in ['C','D']:
  r=subprocess.run(command+[str(d/('separate-'+owner))],env=env,capture_output=True); separate.append(r.returncode)
 assert separate==[0,0]
 rows.append({'script':name,'same_destination_two_owners_exits':exits,'shared_destination_exists':(d/'shared-destination').is_dir(),'existing_destination_control_exit':old.returncode,'distinct_destinations_control_exits':separate,'scope':'unchanged lines8-through-first-mkdir only; synthetic empty ss and barrier mkdir delegates unchanged flags; no renderer/services/media/real-socket observation'})
print(json.dumps({'observations':rows,'actual_media_overwrite_observed':False,'actual_host_interference_observed':False}))
