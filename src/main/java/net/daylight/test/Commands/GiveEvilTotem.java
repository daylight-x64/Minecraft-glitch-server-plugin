package net.daylight.test.Commands;

import net.daylight.test.CustomItems.EvilTotem.EvilTotem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class GiveEvilTotem implements CommandExecutor {

    EvilTotem evilTotem;

    public GiveEvilTotem(EvilTotem evilTotem) {
        this.evilTotem = evilTotem;
    }


    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {

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
        player.give(evilTotem.getEvilTotem());

        player.sendMessage("§cYou feel something watching you...");
        return true;
    }

}
