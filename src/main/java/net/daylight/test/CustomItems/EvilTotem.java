package net.daylight.test.CustomItems;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

@SuppressWarnings("UnstableApiUsage")
public class EvilTotem implements Listener {
    final private Plugin plugin;
    private static final String EVIL_TOTEM_KEY = "evil_totem";

    public EvilTotem(Plugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack getEvilTotem() {
        final ItemStack totem = ItemStack.of(Material.TOTEM_OF_UNDYING);

        totem.setData(DataComponentTypes.ITEM_MODEL, Key.key("daylight", "eviltotem"));
        ItemMeta meta = totem.getItemMeta();

        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, EVIL_TOTEM_KEY), PersistentDataType.BOOLEAN, true);
        meta.displayName(Component.text("Evil Totem", NamedTextColor.DARK_RED));
        totem.setItemMeta(meta);
        //final ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.itemAttributes();
        //builder.addModifier(Attribute.MAX_HEALTH, new AttributeModifier(new NamespacedKey(plugin, "max_health"),10, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
        //totem.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, builder.build());
        //totem.setData(DataComponentTypes.ITEM_MODEL, Key.key(plugin, "eviltotem"));

        return totem;
    }

    public boolean isEvilTotem(ItemStack item) {
        if (item == null || item.getType() != Material.TOTEM_OF_UNDYING) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        return meta.getPersistentDataContainer().has(new NamespacedKey(plugin, EVIL_TOTEM_KEY), PersistentDataType.BOOLEAN);
    }

    @EventHandler(ignoreCancelled = false)
    public void onResurrect(EntityResurrectEvent event) {
        if(!(event.getEntity() instanceof Player player)) return;
        Bukkit.getLogger().info("Resurrect event fired for " + player.getName());

        ItemStack offHand = player.getInventory().getItemInOffHand();
        if(!isEvilTotem(offHand)) return;

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.getInventory().setItemInOffHand(getEvilTotem());
        }, 2L);
    }
}
