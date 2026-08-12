// Core engine & entry (compact)
(() => {
  const canvas = document.getElementById('gameCanvas');
  const ctx = canvas.getContext('2d');
  const TILE = 32;
  let map = { data: [], cols:0, rows:0 };
  let tileImages = new Map();
  let entities = [];

  const player = { x:5*TILE, y:5*TILE, speed:140, dir:'down', frame:0, anim:0, w:TILE, h:TILE, hp:10 };
  let keys = {};
  let camera = {x:0,y:0};

  // --- Asset manager ---
  async function loadImage(path){
    return new Promise((res,rej)=>{ const i=new Image(); i.onload=()=>res(i); i.onerror=()=>rej(path); i.src=path; });
  }

  async function preloadTiles(ids){
    const tasks = ids.map(id=>{
      const name = String(id).padStart(3,'0') + '.png';
      const path = `../res/tiles/${name}`;
      return loadImage(path).then(img=>tileImages.set(id,img)).catch(()=>{});
    });
    await Promise.all(tasks);
  }

  async function preloadPlayer(){
    const paths = ['PlayerDown1','PlayerDown2','PlayerLeft1','PlayerLeft2','PlayerRight1','PlayerRight2','PlayerUp1','PlayerUp2'];
    const sprites = { down:[], left:[], right:[], up:[] };
    try{ sprites.down.push(await loadImage('../res/player/PlayerDown1.png')) }catch{};
    try{ sprites.down.push(await loadImage('../res/player/PlayerDown2.png')) }catch{};
    try{ sprites.left.push(await loadImage('../res/player/PlayerLeft1.png')) }catch{};
    try{ sprites.left.push(await loadImage('../res/player/PlayerLeft2.png')) }catch{};
    try{ sprites.right.push(await loadImage('../res/player/PlayerRight1.png')) }catch{};
    try{ sprites.right.push(await loadImage('../res/player/PlayerRight2.png')) }catch{};
    try{ sprites.up.push(await loadImage('../res/player/PlayerUp1.png')) }catch{};
    try{ sprites.up.push(await loadImage('../res/player/PlayerUp2.png')) }catch{};
    return sprites;
  }

  // --- Map loader ---
  async function loadMapFile(name){
    const res = await fetch(`../res/maps/${name}`);
    const txt = await res.text();
    const lines = txt.split(/\r?\n/).filter(l=>l.trim().length>0);
    const data = lines.map(l=>l.trim().split(/\s+/).map(Number));
    map.data = data; map.rows = data.length; map.cols = data[0].length;
    // collect ids
    const ids = new Set(); for(const r of data) for(const v of r) ids.add(v);
    await preloadTiles(Array.from(ids));
  }

  // --- Simple AI & entities ---
  function spawnEntity(x,y,type){ entities.push({x,y,type,spd:60,dir:'down',hp:5,frame:0,anim:0}); }

  function updateEntities(dt){
    for(const e of entities){
      // simple wander or chase player if close
      const dx = player.x - e.x, dy = player.y - e.y; const d = Math.hypot(dx,dy);
      if(d < 160){ // chase
        e.x += (dx/d) * e.spd * dt;
        e.y += (dy/d) * e.spd * dt;
      } else {
        // wander
        e.anim += dt; if(e.anim>1.2){ e.anim=0; e.dir = ['up','down','left','right'][Math.floor(Math.random()*4)]; }
        if(e.dir==='up') e.y -= e.spd*dt;
        if(e.dir==='down') e.y += e.spd*dt;
        if(e.dir==='left') e.x -= e.spd*dt;
        if(e.dir==='right') e.x += e.spd*dt;
      }
    }
  }

  // --- Audio manager (simple) ---
  const audio = { bgm:null };
  function initAudio(){
    const a = document.getElementById('bgm'); audio.bgm = a; // pre-existing audio tag
  }

  // --- Input ---
  window.addEventListener('keydown', e=>{ keys[e.key]=true; if(['ArrowUp','ArrowDown','ArrowLeft','ArrowRight','w','a','s','d'].includes(e.key)) e.preventDefault(); });
  window.addEventListener('keyup', e=>{ keys[e.key]=false; });

  // --- Update & draw ---
  let sprites = null;
  function update(dt){
    let vx=0, vy=0; let moved=false;
    if(keys['ArrowUp']||keys['w']){ vy -= 1; player.dir='up'; moved=true }
    if(keys['ArrowDown']||keys['s']){ vy += 1; player.dir='down'; moved=true }
    if(keys['ArrowLeft']||keys['a']){ vx -= 1; player.dir='left'; moved=true }
    if(keys['ArrowRight']||keys['d']){ vx += 1; player.dir='right'; moved=true }
    const len = Math.hypot(vx,vy) || 1;
    player.x += (vx/len) * player.speed * dt;
    player.y += (vy/len) * player.speed * dt;
    if(moved){ player.anim += dt; if(player.anim>0.16){ player.anim=0; player.frame=(player.frame+1)%2 }} else { player.frame=0; player.anim=0 }
    updateEntities(dt);
    // camera
    camera.x = player.x - canvas.width/2 + TILE/2; camera.y = player.y - canvas.height/2 + TILE/2;
    camera.x = Math.max(0, Math.min(camera.x, map.cols*TILE - canvas.width));
    camera.y = Math.max(0, Math.min(camera.y, map.rows*TILE - canvas.height));
  }

  function draw(){
    ctx.clearRect(0,0,canvas.width,canvas.height);
    // draw tiles
    const startCol = Math.floor(camera.x / TILE); const endCol = Math.ceil((camera.x+canvas.width)/TILE);
    const startRow = Math.floor(camera.y / TILE); const endRow = Math.ceil((camera.y+canvas.height)/TILE);
    for(let r=startRow;r<endRow;r++){
      if(r<0||r>=map.rows) continue;
      for(let c=startCol;c<endCol;c++){
        if(c<0||c>=map.cols) continue;
        const id = map.data[r][c]; const img = tileImages.get(id);
        const dx = c*TILE - camera.x, dy = r*TILE - camera.y;
        if(img) ctx.drawImage(img, dx, dy, TILE, TILE); else { ctx.fillStyle='#222'; ctx.fillRect(dx,dy,TILE,TILE) }
      }
    }
    // draw entities
    ctx.fillStyle='red'; for(const e of entities){ ctx.fillRect(e.x-camera.x, e.y-camera.y, TILE, TILE) }
    // draw player
    const set = sprites ? (sprites[player.dir]||sprites.down) : null; const img = set ? (set[player.frame]||set[0]) : null;
    const px = player.x - camera.x, py = player.y - camera.y;
    if(img) ctx.drawImage(img, px, py, TILE, TILE); else { ctx.fillStyle='#0f0'; ctx.fillRect(px,py,TILE,TILE) }
    // HUD
    ctx.fillStyle='rgba(0,0,0,0.5)'; ctx.fillRect(8,8,240,36); ctx.fillStyle='#fff'; ctx.font='14px MaruMonica, monospace'; ctx.fillText('HP: '+player.hp, 16, 30);
  }

  // --- Main loop ---
  let last = performance.now();
  function loop(now){ const dt=(now-last)/1000; last=now; update(dt); draw(); requestAnimationFrame(loop); }

  // --- Public init ---
  async function init(){
    initAudio(); sprites = await preloadPlayer();
    // spawn some entities
    spawnEntity(10*TILE,8*TILE,'goblin'); spawnEntity(14*TILE,12*TILE,'orc');
    await loadMapFile('worldmap.txt');
    requestAnimationFrame(loop);
  }

  // map switch buttons
  document.getElementById('map-world').addEventListener('click', async ()=>{ await loadMapFile('worldmap.txt'); player.x=5*TILE; player.y=5*TILE; });
  document.getElementById('map-dungeon').addEventListener('click', async ()=>{ await loadMapFile('dungeon01.txt'); player.x=5*TILE; player.y=5*TILE; });

  // start
  init().catch(e=>{ console.error('Init error', e); alert('Failed to initialize demo: '+e); });
})();
