// Minimal HTML5 port of the game's rendering + movement
// Loads maps from ../res/maps and images from ../res/tiles and ../res/player

const canvas = document.getElementById('gameCanvas');
const ctx = canvas.getContext('2d');

const TILE_SIZE = 32; // pixels per tile
let mapData = [];
let mapCols = 0, mapRows = 0;
let camera = { x:0, y:0 };

const player = {
  x: 5 * TILE_SIZE,
  y: 5 * TILE_SIZE,
  speed: 120, // px/sec
  dir: 'down',
  frame: 0,
  animTimer: 0
};

let keys = {};

function pad3(n){ return String(n).padStart(3,'0') }

async function loadMap(name){
  const res = await fetch(`../res/maps/${name}`);
  const txt = await res.text();
  const lines = txt.split(/\r?\n/).filter(l=>l.trim().length>0);
  mapData = lines.map(line => line.trim().split(/\s+/).map(Number));
  mapRows = mapData.length;
  mapCols = mapData[0].length;
  // preload all tiles used
  const tileIds = new Set();
  for(const row of mapData) for(const v of row) tileIds.add(v);
  await loadTileImages(Array.from(tileIds));
}

const tileImages = new Map();
function loadImage(src){
  return new Promise((res,rej)=>{
    const img = new Image(); img.onload = ()=>res(img); img.onerror = rej; img.src = src;
  });
}

async function loadTileImages(ids){
  const promises = [];
  for(const id of ids){
    const filename = pad3(id) + '.png';
    const path = `../res/tiles/${filename}`;
    // try to load; if missing, ignore
    promises.push(loadImage(path).then(img=>tileImages.set(id,img)).catch(()=>{}));
  }
  await Promise.all(promises);
}

// Player sprites
const playerSprites = {
  down: [], up: [], left: [], right: []
};
async function loadPlayer(){
  const frames = ['1','2'];
  for(const f of frames){
    try{ playerSprites.down.push(await loadImage(`../res/player/PlayerDown${f}.png`)) }catch(e){}
    try{ playerSprites.up.push(await loadImage(`../res/player/PlayerUp${f}.png`)) }catch(e){}
    try{ playerSprites.left.push(await loadImage(`../res/player/PlayerLeft${f}.png`)) }catch(e){}
    try{ playerSprites.right.push(await loadImage(`../res/player/PlayerRight${f}.png`)) }catch(e){}
  }
}

function update(dt){
  let moved = false;
  let vx=0, vy=0;
  if(keys['ArrowUp']||keys['w']){ vy -= 1; player.dir='up'; moved=true }
  if(keys['ArrowDown']||keys['s']){ vy += 1; player.dir='down'; moved=true }
  if(keys['ArrowLeft']||keys['a']){ vx -= 1; player.dir='left'; moved=true }
  if(keys['ArrowRight']||keys['d']){ vx += 1; player.dir='right'; moved=true }
  const len = Math.hypot(vx,vy)||1;
  player.x += (vx/len) * player.speed * dt;
  player.y += (vy/len) * player.speed * dt;

  if(moved){
    player.animTimer += dt;
    if(player.animTimer > 0.18){ player.animTimer = 0; player.frame = (player.frame+1) % 2 }
  } else { player.frame = 0; player.animTimer = 0 }

  // camera centers on player
  camera.x = player.x - canvas.width/2 + TILE_SIZE/2;
  camera.y = player.y - canvas.height/2 + TILE_SIZE/2;
  camera.x = Math.max(0, Math.min(camera.x, mapCols*TILE_SIZE - canvas.width));
  camera.y = Math.max(0, Math.min(camera.y, mapRows*TILE_SIZE - canvas.height));
}

function draw(){
  ctx.clearRect(0,0,canvas.width,canvas.height);
  // draw tiles
  const startCol = Math.floor(camera.x / TILE_SIZE);
  const endCol = Math.ceil((camera.x + canvas.width) / TILE_SIZE);
  const startRow = Math.floor(camera.y / TILE_SIZE);
  const endRow = Math.ceil((camera.y + canvas.height) / TILE_SIZE);

  for(let r = startRow; r < endRow; r++){
    if(r<0 || r>=mapRows) continue;
    for(let c = startCol; c < endCol; c++){
      if(c<0 || c>=mapCols) continue;
      const id = mapData[r][c];
      const img = tileImages.get(id);
      if(img){
        const dx = c*TILE_SIZE - camera.x;
        const dy = r*TILE_SIZE - camera.y;
        ctx.drawImage(img, dx, dy, TILE_SIZE, TILE_SIZE);
      } else {
        // missing tile: draw placeholder
        ctx.fillStyle = '#222';
        ctx.fillRect(c*TILE_SIZE - camera.x, r*TILE_SIZE - camera.y, TILE_SIZE, TILE_SIZE);
      }
    }
  }

  // draw player
  const sprites = playerSprites[player.dir] || playerSprites.down;
  const img = sprites[player.frame] || sprites[0];
  const px = player.x - camera.x;
  const py = player.y - camera.y;
  if(img) ctx.drawImage(img, px, py, TILE_SIZE, TILE_SIZE);
  else { ctx.fillStyle='#0f0'; ctx.fillRect(px,py,TILE_SIZE,TILE_SIZE) }

  // HUD
  ctx.fillStyle = 'rgba(0,0,0,0.5)'; ctx.fillRect(8,8,160,36);
  ctx.fillStyle = '#fff'; ctx.font = '14px MaruMonica, monospace';
  ctx.fillText('Map demo — use arrows / WASD', 14, 30);
}

let last = performance.now();
function loop(now){
  const dt = (now - last)/1000; last = now;
  update(dt);
  draw();
  requestAnimationFrame(loop);
}

// Map switching UI
document.getElementById('map-world').addEventListener('click', async ()=>{
  await loadMap('worldmap.txt');
  player.x = 5*TILE_SIZE; player.y = 5*TILE_SIZE;
});
document.getElementById('map-dungeon').addEventListener('click', async ()=>{
  await loadMap('dungeon01.txt');
  player.x = 5*TILE_SIZE; player.y = 5*TILE_SIZE;
});

// music toggle and selection
const bgm = document.getElementById('bgm');
const bgmSrc = document.getElementById('bgm-src');
document.getElementById('music-toggle').addEventListener('click', ()=>{
  if(bgm.paused) bgm.play(); else bgm.pause();
});

// input
window.addEventListener('keydown', e=>{ keys[e.key]=true });
window.addEventListener('keyup', e=>{ keys[e.key]=false });

// initialize
(async function init(){
  await loadPlayer();
  // default: load world map and set music
  await loadMap('worldmap.txt');
  bgmSrc.src = '../res/sound/titlescreenmusic.wav';
  bgm.load();
  requestAnimationFrame(loop);
})();
