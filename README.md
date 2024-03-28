# MotionPlugin
#### By Sw3d15h_F1s4

## Description

This is a *very* simple Minecraft Spigot plugin. It adds two commands:
 - `/motion <x> <y> <z>`
 - `/relmotion <l> <u> <f>`

The `/motion` command can be executed by a player or entity and will set their motion in global coordinates, that is if you call `/motion 1 0 0` you will move at 1 block/tick in the positive X direction.
This is equivalent to setting the `Motion:[0.0f,0.0f,0.0f]` NBT data for an entity. The upside here is that this command works on all entities, including players.

The `/relmotion` command is similar to the `/motion` command, except it uses the entity's local coordinates. The syntax is similar to vanilla's local coordinates system (`^l ^r ^f`).
 - `l` moves the entity to their left (negative values to the right).
 - `u` moves the entity up (negative moves down).
 - `f` moves the entity forward (negative for backwards).

## Benefits

This plugin uses [CommandAPI](https://github.com/JorelAli/CommandAPI), which means that both of these commands can be used with `/execute` or inside a datapack with `.mcfunction`.


## Compatibility

As of the time of writing, this plugin works with Minecraft Spigot versions 1.20.3 and 1.20.4. I haven't tested anything else.

I don't plan on maintaining this plugin much either. Compiling for different versions should be as easy as changing the `plugin.yml` and `pom.xml` files.

## Installation

Installing this plugin should be as easy as downloading the latest release (look to the right side of the page) and putting the jar file inside your server's `plugins` folder. No other plugins are required.
