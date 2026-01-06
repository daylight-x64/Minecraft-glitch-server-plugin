package net.daylight.test;

import net.daylight.test.ChunksEffects.ChunksMain;
import net.daylight.test.Commands.GiveEvilTotem;
import net.daylight.test.CustomItems.EvilTotem;
import net.daylight.test.Glitches.GhostBlocks;
import net.daylight.test.Glitches.RandomDrop;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin has been enabled");
        getServer().getPluginManager().registerEvents(new RandomDrop(), this);
        getServer().getPluginManager().registerEvents(new GhostBlocks(this), this);
        getServer().getPluginManager().registerEvents(new ChunksMain(this), this);
        EvilTotem totemHandler = new EvilTotem(this);
        getServer().getPluginManager().registerEvents(totemHandler, this);
        getCommand("evilTotem").setExecutor(new GiveEvilTotem(totemHandler));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
