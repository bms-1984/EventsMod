package io.bms.events.parsing;

import io.bms.events.EventsMod;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;

/**
 * Created by benjaminsutter on 7/20/17.
 */
public class JSFunctions {
    private static Server server;

    public JSFunctions(Server server) {
        this.server = server;
    }

    public static void startRain() {
        World world = server.getWorlds().get(0);
        world.setStorm(true);
    }

    public static void broadcastMessage(String message) {
        server.broadcastMessage(message);
    }

    public static void spawnEntity(int worldID, int x, int y, int z, Class<Entity> entity) {
        World world = server.getWorlds().get(worldID);
        Location location = new Location(world, (double) x, (double) y, (double) z);
        world.spawn(location, entity);
    }

    public static void spawnMythicEntity(int worldID, int x, int y, int z, String entityName, int level) {
        World world = server.getWorlds().get(worldID);
        Location location = new Location(world, (double) x, (double) y, (double) z);
        if (EventsMod.getInstance().getServer().getPluginManager().getPlugin("MythicMobs") == null) {
            EventsMod.logger.warning("MythicMobs is not present, this feature may not be used.");
        }
        else {
            ActiveMob mob = MythicMobs.inst().getMobManager().spawnMob(entityName, location, level);
            if (mob == null) {
                EventsMod.logger.warning("A mythic mob with that name doesn't exist. Check for it with /mm m list.");
            }
            else {
                EventsMod.logger.info(String.format("A mythic mob called %s was spawned.", entityName));
            }
        }
    }

    public static void killAllMythicEntities() {
        if (EventsMod.getInstance().getServer().getPluginManager().getPlugin("MythicMobs") == null) {
            EventsMod.logger.warning("MythicMobs is not present, this feature may not be used.");
        }
        else {
            EventsMod.logger.info("Removing all mythic mobs.");
            MythicMobs.inst().getMobManager().despawnAllMobs();
        }
    }

    // be careful; this does block damage
    public static void explode(int worldID, int x, int y, int z, int radius) {
        World world = server.getWorlds().get(worldID);
        Location location = new Location(world, (double) x, (double) y, (double) z);
        world.createExplosion(location, (float) radius);
    }

    public static void moveBlock(int worldID1, int x1, int y1, int z1, int worldID2, int x2, int y2, int z2) {
        World world1 = server.getWorlds().get(worldID1);
        World world2 = server.getWorlds().get(worldID2);
        Location location1 = new Location(world1, (double) x1, (double) y1, (double) z1);
        Location location2 = new Location(world2, (double) x2, (double) y2, (double) z2);
        location2.getBlock().setType(location1.getBlock().getType());
        location1.getBlock().setType(Material.AIR);
    }

    public static void spawnBlock(int worldID, int x, int y, int z, Material block) {
        World world = server.getWorlds().get(worldID);
        Location location = new Location(world, (double) x, (double) y, (double) z);
        location.getBlock().setType(block);
    }
}
