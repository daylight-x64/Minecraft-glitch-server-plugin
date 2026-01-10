package net.daylight.test.Commands;

import net.daylight.test.CustomItems.GlitchFragments.GlitchFragment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jspecify.annotations.NonNull;

public class GlitchFragmentComamnd implements CommandExecutor {
    GlitchFragment fragment;
    Plugin plugin;


    public GlitchFragmentComamnd(Plugin plugin) {
        this.plugin = plugin;
        fragment = new GlitchFragment(plugin);
    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String [] args) {

        // Only players
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this.");
            return true;
        }

        // Permission check
        if (!player.hasPermission("glitch.evilTotem")) {
            player.sendMessage("No permission.");
            return true;
        }

        // Give item
        player.give(fragment.getGlitchFragment(1));

        return true;
    }
}
