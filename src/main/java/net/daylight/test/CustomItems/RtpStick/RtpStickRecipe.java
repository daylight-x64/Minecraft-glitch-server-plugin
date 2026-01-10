package net.daylight.test.CustomItems.RtpStick;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class RtpStickRecipe {

    public RtpStickRecipe(Plugin plugin) {
        final ItemStack diamond = new ItemStack(Material.DIAMOND);
        final ItemMeta meta = diamond.getItemMeta();
        meta.displayName(Component.text("Special Diamond", NamedTextColor.DARK_PURPLE));
        diamond.setItemMeta(meta);



        final NamespacedKey key = new NamespacedKey(plugin, "custom_recipe");
        final ShapedRecipe recipe = new ShapedRecipe(key, diamond);
        recipe.shape(
                "GGG",
                "GIG",
                "GGG"
        );
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('I', Material.IRON_INGOT);

        Bukkit.addRecipe(recipe);

    }
}
