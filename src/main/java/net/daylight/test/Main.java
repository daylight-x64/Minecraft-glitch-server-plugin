package net.daylight.test;

import net.daylight.test.Commands.GlitchFragmentComamnd;
import net.daylight.test.CustomItems.GlitchCore.GlitchCoreMain;
import net.daylight.test.CustomItems.GlitchFragments.GlitchFragmentListener;
import net.daylight.test.CustomItems.RtpStick.RandomTp;
import net.daylight.test.ChunksEffects.ChunksMain;
import net.daylight.test.Commands.GiveEvilTotem;
import net.daylight.test.Commands.RandomTpItem;
import net.daylight.test.CustomItems.EvilTotem.EvilTotem;
import net.daylight.test.CustomItems.RtpStick.RtpStickRecipe;
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
        RandomTp randomTp = new RandomTp(this);
        getServer().getPluginManager().registerEvents(randomTp, this);
        getCommand("rtpItem").setExecutor(new RandomTpItem(randomTp));


        getCommand("glitchFragment").setExecutor(new GlitchFragmentComamnd(this));
        getServer().getPluginManager().registerEvents(new GlitchFragmentListener(this), this);

        EvilTotem totemHandler = new EvilTotem(this);
        getServer().getPluginManager().registerEvents(totemHandler, this);
        getCommand("evilTotem").setExecutor(new GiveEvilTotem(totemHandler));

        RtpStickRecipe stickRecipe = new RtpStickRecipe(this);
        GlitchCoreMain glitchCoreMain = new GlitchCoreMain(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
