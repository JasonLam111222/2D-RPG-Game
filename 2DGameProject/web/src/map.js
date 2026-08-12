// map module source
export async function loadMapFile(path){ const res = await fetch(path); const txt = await res.text(); const lines = txt.split(/\r?\n/).filter(l=>l.trim().length>0); return lines.map(l=>l.trim().split(/\s+/).map(Number)); }
