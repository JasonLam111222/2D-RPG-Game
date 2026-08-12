// ai module source (placeholder)
export function simpleAI(e,player,dt){ const dx=player.x-e.x, dy=player.y-e.y; const d=Math.hypot(dx,dy)||1; if(d<160){ e.x += (dx/d)*e.spd*dt; e.y += (dy/d)*e.spd*dt; } }
