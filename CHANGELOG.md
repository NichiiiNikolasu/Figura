# Changelog — Figura 1.21.11

All notable changes since the initial 1.21.11 port.

## Bug Fixes

### Rendering
- **Fix cape visibility** — Capes were not rendering correctly for players
- **Fix skull block entity rendering** — Skull blocks placed in the world were not rendering properly
- **Fix hand-held/hotbar skull rendering** — First-person held heads and hotbar skull items were broken
- **Fix duplicate ghost skull with shaders** — A ghost skull would appear when using Iris/Optifine shader packs

### Textures & Crashes
- **Fix texture crash on avatar reload** — `Texture view does not exist` crash when clearing/reloading avatars. The GPU texture was being freed while in-flight render batches (Sodium shadow passes, ImmediatelyFast batched draws, Iris) still referenced it. Texture GPU resources are now left for GC cleanup instead of manual release during avatar unload
- **Fix missing textures on resource reload** — `FiguraTexture.close()` now only releases GPU resources (not NativeImage data), so `apply()` can re-upload the texture after a resource pack reload
- **Fix texture cleanup race condition** — Texture destruction is always deferred to the render thread to prevent async threads from destroying textures mid-frame

### Emojis
- **Fix animated emoji metadata being null** — `GlyphInfo.simple()` creates lambda instances with no `equals/hashCode`, so the HashMap in `GlyphStitcherMixin` never matched. Added a fallback in `FontSetMixin.afterComputeGlyphInfo` that directly sets up emoji metadata after glyph baking
- **Fix animated emoji UV with ImmediatelyFast** — Replaced hardcoded `8f / getFontWidthIMF()` with dynamic `(u1 - u0) / frames` for per-frame UV width, making emoji animation independent of font atlas size (works with vanilla 256px, IF's 2048px, or any other size)

### Stability
- **Fix ConcurrentModificationException in feature renderer** — Figura callbacks during `renderModel` could indirectly submit new translucent models, modifying the list while iterating. Added defensive copy in `ModelFeatureRendererMixin.renderTranslucents`

### CI/Build
- **Fix gradlew executable permission** — Linux CI builds would fail due to missing execute permission on `gradlew`
