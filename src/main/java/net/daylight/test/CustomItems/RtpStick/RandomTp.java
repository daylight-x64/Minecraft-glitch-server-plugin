package net.daylight.test.CustomItems.RtpStick;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.ThreadLocalRandom;

public class RandomTp implements Listener {
    Plugin plugin;



    public RandomTp(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {

        if(!(e.getAction() == Action.RIGHT_CLICK_AIR ||
                e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        //Check if right item
        Player player =e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(!(isRtpItem(item))) return;

        if(player.hasCooldown(item.getType())) {
            player.sendMessage("§cYour RTP stick is recharging...");
            return;
        }


        player.setCooldown(item.getType(), 20 * 30); // 30 seconds

        Location orgin = player.getLocation();
        Location newLoc = (randomNearby(orgin, 8));
        if(newLoc == null) {
            player.sendMessage("Couldn't find valid location");
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
            Location safeY = findHighestPointAndCheckIfSafe(newLoc);
            if(safeY == null) continue;
            return safeY;

        }
        return null;
    }

    public boolean locationIsSafe(Location loc) {
        return loc.getBlock().getType() == Material.AIR ||
                loc.getBlock().getType() == Material.SHORT_GRASS ||
                loc.getBlock().getType() == Material.LEAF_LITTER;
    }


    public Location findHighestPointAndCheckIfSafe(Location loc) {
        for (int i = -3; i < 3; i++) {
            Location base = loc.clone().add(0, i-1, 0);

            if (!base.getBlock().isSolid()) continue;

            Location feet = loc.clone().add(0, i, 0);
            Location head = loc.clone().add(0, i + 1, 0);

            if (!locationIsSafe(feet)) continue;
            if (!locationIsSafe(head)) continue;

            return loc.clone().add(0, i, 0);

        }
        return null;
    }

    private static final String RTPITEM = "rtpitem";

    public ItemStack getRtpItem() {
        final ItemStack stick = ItemStack.of(Material.STICK);

        //stick.setData(DataComponentTypes.ITEM_MODEL, Key.key("daylight", RTPITEM));
        ItemMeta meta = stick.getItemMeta();

        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, RTPITEM), PersistentDataType.BOOLEAN, true);
        meta.displayName(Component.text("Rtp item", NamedTextColor.DARK_RED));
        stick.setItemMeta(meta);

        return stick;
    }

    public boolean isRtpItem(ItemStack item) {
        if (item == null || item.getType() != Material.STICK) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        return meta.getPersistentDataContainer().has(new NamespacedKey(plugin, RTPITEM), PersistentDataType.BOOLEAN);
    }

}
