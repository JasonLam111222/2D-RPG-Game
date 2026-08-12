# HTML5 Demo — initial port

This folder contains a minimal HTML5 demo of the 2D RPG project.

What it includes
- index.html, style.css, game.js — a small Canvas-based renderer + player movement
- References to the game's assets inside ../res (tiles, player sprites, maps, sounds, font)

Maps included (from the original project)
- res/maps/worldmap.txt
- res/maps/dungeon01.txt

Audio & fonts
- The demo references the original .wav music/sounds in res/sound and the TTF in res/font.
  Those are large files but already present in the repo; the demo will load them from their existing paths.

How to run locally
1. Clone the repo and checkout branch `html-port`.
2. From the repo root run a simple local server, for example:

   python3 -m http.server 8000

3. Open http://localhost:8000/2DGameProject/web/index.html in your browser.

Notes & next steps
- This is intentionally minimal: it renders tile maps and lets you move the player. It does not yet
  implement enemies, inventory, UI screens, or full collision logic from the Java code.
- If you'd like I can:
  - Improve collisions using the Java tile data, port monsters/AI, and port more game subsystems.
  - Optimize asset loading and create a build (single-page distribution) or GitHub Pages deployment.
