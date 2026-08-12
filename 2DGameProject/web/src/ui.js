// ui module source
export function createButton(id,fn){ const b=document.getElementById(id); if(b) b.addEventListener('click',fn); }
