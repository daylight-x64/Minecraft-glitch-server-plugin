package net.daylight.test.Abilities;

import net.daylight.test.Commands.RandomTpItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.ThreadLocalRandom;

public class RandomTp implements Listener {
    RandomTpItem rtpItem;
    Plugin plugin;



    public RandomTp(Plugin plugin) {
        this.plugin = plugin;
        rtpItem = new RandomTpItem(plugin);
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {

        if(!(e.getAction() == Action.RIGHT_CLICK_AIR ||
                e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        //Check if right item
        Player player =(Player) e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(!(rtpItem.isRtpItem(item))) return;

        Location orgin = player.getLocation();
        Location newLoc = (randomNearby(orgin, 8));
        if(newLoc == null) {
            player.sendMessage("Couldnt find valid location");
            return;
        }
        player.teleport(newLoc);

    }

    public Location randomNearby(Location origin, double radius) {
        for (int i = 0; i < 10; i++) {
            double angle = ThreadLocalRandom.current().nextDouble(0, Math.PI * 2);
            double distance = ThreadLocalRandom.current().nextDouble(0, radius);

            double x = Math.cos(angle) * distance;
            double z = Math.sin(angle) * distance;
            Location newLoc = origin.clone().add(x, 0, z);
            if(findHighestPointAndCheckIfSafe(newLoc)) {
                return origin.clone().add(x, 0, z);
            }
        }
        return null;
    }

    public boolean locationIsSafe(Location loc) {
        return loc.getBlock().getType() == Material.AIR ||
                loc.getBlock().getType() == Material.SHORT_GRASS ||
                loc.getBlock().getType() == Material.LEAF_LITTER;
    }


    public boolean findHighestPointAndCheckIfSafe(Location loc) {
        for (int i = -3; i < 3; i++) {
            Location base = loc.clone().add(0, i, 0);

            if (!base.getBlock().isSolid()) continue;

            Location head = loc.clone().add(0, i + 1, 0);
            Location aboveHead = loc.clone().add(0, i + 2, 0);

            if (!locationIsSafe(head)) continue;
            if (!locationIsSafe(aboveHead)) continue;

            return true;

        }
        return false;
    }

}
