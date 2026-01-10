package net.daylight.test.CustomItems.GlitchCore;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class GlitchCoreMain {
    private static final String GLITCH_FRAGMENT = "glitchfragment";
    Plugin plugin;
    Recipe recipe;


    public GlitchCoreMain(Plugin plugin) {
        this.plugin = plugin;
        recipe = new Recipe(plugin, this);
    }



    public ItemStack getGlitchCore(Integer amount) {
        final ItemStack item = ItemStack.of(Material.RABBIT_HIDE, amount);

        //item.setData(DataComponentTypes.ITEM_MODEL, Key.key("daylight", "GLITCH_FRAGMENT"));
        ItemMeta meta = item.getItemMeta();

        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, GLITCH_FRAGMENT), PersistentDataType.BOOLEAN, true);
        meta.displayName(Component.text("Glitch fragment", NamedTextColor.BLACK, TextDecoration.OBFUSCATED));

        item.setItemMeta(meta);

        return item;
    }

    public boolean isGlitchFragment(ItemStack item) {
        if (item == null || item.getType() != Material.RABBIT_HIDE) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        return meta.getPersistentDataContainer().has(new NamespacedKey(plugin, GLITCH_FRAGMENT), PersistentDataType.BOOLEAN);
    }
}
