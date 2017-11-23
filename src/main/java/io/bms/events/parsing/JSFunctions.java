package io.bms.events.parsing;

import io.bms.events.EventsMod;
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
        JSFunctions.server = server;
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
        if (world != null) {
            Location location = new Location(world, (double) x, (double) y, (double) z);
            world.spawn(location, entity);
        }
        else {
            EventsMod.logger.warning("Could not spawn an entity because the specified world does not exist.");
        }
    }

    // be careful; this does block damage
    public static void explode(int worldID, int x, int y, int z, int radius) {
        World world = server.getWorlds().get(worldID);
        if (world != null) {
            Location location = new Location(world, (double) x, (double) y, (double) z);
            world.createExplosion(location, (float) radius);
        }
        else {
            EventsMod.logger.warning("Could not spawn an explosion because the specified world does not exist.");
        }
    }

    public static void moveBlock(int worldID1, int x1, int y1, int z1, int worldID2, int x2, int y2, int z2) {
        World world1 = server.getWorlds().get(worldID1);
        World world2 = server.getWorlds().get(worldID2);
        if (world1 != null && world2 != null) {
            Location location1 = new Location(world1, (double) x1, (double) y1, (double) z1);
            Location location2 = new Location(world2, (double) x2, (double) y2, (double) z2);
            location2.getBlock().setType(location1.getBlock().getType());
            location1.getBlock().setType(Material.AIR);
        }
        else {
            EventsMod.logger.warning("Could not spawn an entity because one or both of the specified worlds does not exist.");
        }
    }

    public static void spawnBlock(int worldID, int x, int y, int z, Material block) {
        World world = server.getWorlds().get(worldID);

        if (world != null) {
            Location location = new Location(world, (double) x, (double) y, (double) z);
            location.getBlock().setType(block);
        }
        else {
            EventsMod.logger.warning("Could not spawn an entity because the specified world does not exist.");
        }
    }
}
