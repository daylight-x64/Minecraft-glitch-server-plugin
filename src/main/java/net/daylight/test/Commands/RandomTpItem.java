package net.daylight.test.Commands;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public class RandomTpItem implements CommandExecutor {
    final private Plugin plugin;
    private static final String RTPITEM = "rtpitem";

    public RandomTpItem(Plugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack getRtpItem() {
        final ItemStack stick = ItemStack.of(Material.STICK);

        //stick.setData(DataComponentTypes.ITEM_MODEL, Key.key("daylight", RTPITEM));
        ItemMeta meta = stick.getItemMeta();

        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, RTPITEM), PersistentDataType.BOOLEAN, true);
        meta.displayName(Component.text("Rtp item", NamedTextColor.DARK_RED));
        stick.setItemMeta(meta);
        //final ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.itemAttributes();
        //builder.addModifier(Attribute.MAX_HEALTH, new AttributeModifier(new NamespacedKey(plugin, "max_health"),10, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
        //totem.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, builder.build());
        //totem.setData(DataComponentTypes.ITEM_MODEL, Key.key(plugin, RTPITEM));

        return stick;
    }

    public boolean isRtpItem(ItemStack item) {
        if (item == null || item.getType() != Material.STICK) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        return meta.getPersistentDataContainer().has(new NamespacedKey(plugin, RTPITEM), PersistentDataType.BOOLEAN);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("Only players can execute this command");
            return false;
        }

        player.give(getRtpItem());
        return true;
    }
}
