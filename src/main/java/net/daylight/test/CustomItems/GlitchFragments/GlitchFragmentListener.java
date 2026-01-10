package net.daylight.test.CustomItems.GlitchFragments;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class GlitchFragmentListener implements Listener {
    GlitchFragment fragment;

    HashMap<Material, Integer> blocksDropFragments = new HashMap<>();


    public GlitchFragmentListener(Plugin plugin) {
        fragment = new GlitchFragment(plugin);
        getList();

    }

    public void getList() {
        blocksDropFragments.put(Material.COAL_ORE, 5);
        blocksDropFragments.put(Material.COPPER_ORE, 8);
        blocksDropFragments.put(Material.IRON_ORE, 10);
        blocksDropFragments.put(Material.POINTED_DRIPSTONE, 15);
        blocksDropFragments.put(Material.MOSSY_COBBLESTONE, 35);
        blocksDropFragments.put(Material.SPAWNER, 100);
    }

    @EventHandler
    public void onPlayerMine(BlockBreakEvent e)  {
        Player player = e.getPlayer();
        if(player.getGameMode() != GameMode.SURVIVAL) return;
        Block block = e.getBlock();
        Material material = block.getType();
        if(!(blocksDropFragments.containsKey(material))) return;
        if(ThreadLocalRandom.current().nextInt(100) >= blocksDropFragments.get(material)) return;

        ItemStack mainHand = player.getInventory().getItemInMainHand();
        ItemMeta meta = mainHand.getItemMeta();
        ItemStack item = null;
        if(meta != null) {
            if(meta.getEnchants().containsKey(Enchantment.FORTUNE)) {
                int level = meta.getEnchantLevel(Enchantment.FORTUNE);
                item = fragment.getGlitchFragment(ThreadLocalRandom.current().nextInt(level) + 1);
                player.give(item);
            }
        }
        e.setDropItems(false);
        if(item == null) {
            item = (fragment.getGlitchFragment(1));

        }
        block.getWorld().dropItemNaturally(block.getLocation(), item);


    }
}
