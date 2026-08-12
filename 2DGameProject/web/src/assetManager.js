// asset manager source
export async function loadImage(path){ return new Promise((res,rej)=>{ const i=new Image(); i.onload=()=>res(i); i.onerror=()=>rej(path); i.src=path; }); }
