package net.daylight.test.Commands;


import net.daylight.test.CustomItems.RtpStick.RandomTp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RandomTpItem implements CommandExecutor {
    RandomTp rtpStick;


    public RandomTpItem(RandomTp rtpStick) {
        this.rtpStick = rtpStick;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("Only players can execute this command");
            return false;
        }

        player.give(rtpStick.getRtpItem());
        return true;
    }
}
