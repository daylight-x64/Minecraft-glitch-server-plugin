package net.daylight.test;

import net.daylight.test.Abilities.RandomTp;
import net.daylight.test.ChunksEffects.ChunksMain;
import net.daylight.test.Commands.GiveEvilTotem;
import net.daylight.test.Commands.RandomTpItem;
import net.daylight.test.CustomItems.EvilTotem;
import net.daylight.test.PassiveGlitches.GhostBlocks;
import net.daylight.test.PassiveGlitches.RandomDrop;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("Plugin has been enabled");
        //Passive glitches
        getServer().getPluginManager().registerEvents(new RandomDrop(), this);
        getServer().getPluginManager().registerEvents(new GhostBlocks(this), this);

        //Chunk glitches
        getServer().getPluginManager().registerEvents(new ChunksMain(this), this);

        //Abilities/special items
        getServer().getPluginManager().registerEvents(new RandomTp(this), this);
        getCommand("rtpItem").setExecutor(new RandomTpItem(this));

        EvilTotem totemHandler = new EvilTotem(this);
        getServer().getPluginManager().registerEvents(totemHandler, this);
        getCommand("evilTotem").setExecutor(new GiveEvilTotem(totemHandler));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
