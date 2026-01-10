package net.daylight.test.CustomItems.GlitchCore;

import net.daylight.test.CustomItems.GlitchFragments.GlitchFragment;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class Recipe {
    public static String GLITCHCORE_RECIPE = "glitchcore";


    public Recipe(Plugin plugin, GlitchCoreMain main) {
        GlitchFragment glitchFragment = new GlitchFragment(plugin);

        ItemStack core = main.getGlitchCore(1);

        final NamespacedKey key = new NamespacedKey(plugin, GLITCHCORE_RECIPE);
        final ShapedRecipe recipe = new ShapedRecipe(key, core);
        recipe.shape(
                " R ",
                "RDR",
                " R "
        );
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(glitchFragment.getGlitchFragment(1)));

        recipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(recipe);
    }
}
