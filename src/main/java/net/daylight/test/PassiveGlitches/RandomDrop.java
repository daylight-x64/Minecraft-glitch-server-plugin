package net.daylight.test.PassiveGlitches;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDrop implements Listener {
    private static final List<Material> DROP_MATERIALS = Arrays.stream(Material.values())
            .filter(Material::isItem)
            .filter(m -> m != Material.AIR)
            .toList();


    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Random random = new Random();
        if (!(random.nextInt(20) == 1)) return;
        Material newItem = DROP_MATERIALS.get(ThreadLocalRandom.current().nextInt(DROP_MATERIALS.size()));
        event.getItemDrop().setItemStack(new ItemStack(newItem));
    }
}
