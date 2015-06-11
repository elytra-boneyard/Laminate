# Laminate
An awesome GUI framework for Minecraft Forge - a rewrite of Glass Pane

## Goals
 - Make it easy to create GUIs that can act as replacements, overlays, etc
 - Be fast (would display lists and/or VBOs be overkill?)
 - Be pretty
 - Be accessible
 - Be customizable
 - Make the codebase work across versions by wrapping all version-specific calls in an abstraction layer (would this also make it work across runtimes?)
 - Fit in with vanilla GuiScreens
 - Port features (like focus control) to GuiScreen optionally for a consistent experience

## Changes to make from Glass Pane
 - Stop prefixing everything with "Pane"
 - Don't use Lombok
 - Automatically prevent loading on the server-side
 - Allow serialization and deserialization of GUIs
 - Don't use a custom event system
 - Cut back on the preemptive micro-optimizations
 - Make the source code less than 50% comments

## Wishlist
 - UI designer via the serialized format
 - Allow server to send GUIs via the serialized format
 - Easy inventory GUI with dynamically-rendered slots
