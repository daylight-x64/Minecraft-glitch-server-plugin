package net.daylight.test.Glitches;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class GhostBlocks implements Listener {
    private final Random random = new Random();
    private final Plugin plugin;

    public GhostBlocks(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(!(random.nextInt(2) == 1)) return;
        Player player = event.getPlayer();
        Location blockLoc = event.getBlockPlaced().getLocation();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.sendBlockChange(blockLoc, Material.AIR.createBlockData());
        }, 2L);
        long currentDelay = 2L;
        int repeatTimes = 1 + random.nextInt(2) * 2;
        for (int i = 0; i < repeatTimes; i++) {
            int finalI = i;
            long randomGap = 3L + random.nextInt(8);
            currentDelay += randomGap;
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                boolean hide = finalI % 2 == 0;

                player.sendBlockChange(
                        blockLoc,
                        hide
                                ? Material.AIR.createBlockData()
                                : blockLoc.getBlock().getBlockData()
                );

            }, randomGap);
        }
        long finalDelay = currentDelay + 3;

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.sendBlockChange(blockLoc, blockLoc.getBlock().getBlockData());
        }, finalDelay);

    }
}
