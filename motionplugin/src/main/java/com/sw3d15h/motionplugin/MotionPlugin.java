package com.sw3d15h.motionplugin;


import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.FloatArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.executors.CommandArguments;

public class MotionPlugin extends JavaPlugin {

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).verboseOutput(true));
    }

    @SuppressWarnings("null")
    @Override
    public void onEnable() {
        CommandAPI.onEnable();
        getLogger().info("motion plugin: onEnable is called!");

        new CommandAPICommand("motion")
        .withArguments(new IntegerArgument("x"))
        .withArguments(new IntegerArgument("y"))
        .withArguments(new IntegerArgument("z"))
        .executesEntity((Entity sender, CommandArguments args) ->{
            sender.setVelocity(new Vector((int)args.get("x"),(int)args.get("y"),(int)args.get("z")));
        })
        .executesPlayer((Player player, CommandArguments args) -> {
            player.setVelocity(new Vector((int)args.get("x"),(int)args.get("y"),(int)args.get("z")));
        })
        .executesProxy((proxy, args) -> {
            if (proxy.getCallee() instanceof Entity sender){
                sender.setVelocity(new Vector((int)args.get("x"),(int)args.get("y"),(int)args.get("z")));
            }
        })
        .register();

        new CommandAPICommand("relmotion")
        .withArguments(new FloatArgument("l"))
        .withArguments(new FloatArgument("u"))
        .withArguments(new FloatArgument("f"))
        .executesPlayer((Player sender, CommandArguments args) -> {
            sender.setVelocity(relativeMotion(sender, (float)args.get("l"), (float)args.get("u"), (float)args.get("f")));
        })
        .executesEntity((Entity sender, CommandArguments args) -> {
            sender.setVelocity(relativeMotion(sender, (float)args.get("l"), (float)args.get("u"), (float)args.get("f")));
        })
        .executesProxy((proxy, args) -> {
            if (proxy.getCallee() instanceof Entity sender){
                sender.setVelocity(relativeMotion(sender, (float)args.get("l"), (float)args.get("u"), (float)args.get("f")));
            }
        })
        .register();
    }

    // I know its a mess, but i'm lazy and it works.
    public Vector relativeMotion(Entity sender, float l, float u, float f) {
        Location location = sender.getLocation();
        float yaw = location.getYaw() * (float)Math.PI / 180;
        float pitch = location.getPitch() * (float)Math.PI / 180;

        float sy = (float)Math.sin(yaw);
        float cy = (float)Math.cos(yaw);
        float sp = (float)Math.sin(pitch);
        float cp = (float)Math.cos(pitch);

        float f_x = -1 * f * sy * cp;
        float f_y = -1 * f * sp;
        float f_z = f * cy * cp;
        
        float l_z = l * sy;
        float l_x = l * cy;
        
        float u_x = -1 * u * sy * sp;
        float u_z = u * cy * sp;
        float u_y = u * cp;
        
        float motionX = f_x + l_x + u_x;
        float motionY = f_y + u_y;
        float motionZ = f_z + l_z + u_z;
        return new Vector(motionX, motionY, motionZ);
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
        getLogger().info("motion plugin: onDisable is called!");
    }
}