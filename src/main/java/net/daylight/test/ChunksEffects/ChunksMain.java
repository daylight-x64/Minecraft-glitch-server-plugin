package net.daylight.test.ChunksEffects;

import net.daylight.test.Glitches.GhostBlocks;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.ThreadLocalRandom;

public class ChunksMain implements Listener {
    public static NamespacedKey CHUNK_TYPE;

    public ChunksMain(Plugin plugin) {

        CHUNK_TYPE = new NamespacedKey(plugin, "chunk_type");
        plugin.getServer().getPluginManager().registerEvents(new ChunksPlayerHandler(this), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ChunkGlitches(this), plugin);
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {
        if(!e.isNewChunk()) return;

        Chunk chunk = e.getChunk();
        PersistentDataContainer pdc = chunk.getPersistentDataContainer();

        if(!pdc.has(CHUNK_TYPE, PersistentDataType.INTEGER)) {
            boolean special = ThreadLocalRandom.current().nextInt(5) == 2;

            if(special) {
                pdc.set(CHUNK_TYPE, PersistentDataType.INTEGER, 1);
            }
        }
    }

    public void playerInSpecialChunk(Player player) {
        player.sendMessage("Your in a special chunk");
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 10, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20 * 10, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 20 * 10, 1));

    }
    public void playerNotInSpecialChunk(Player player) {
        player.sendMessage("Your not in a special chunk");
        player.removePotionEffect(PotionEffectType.WEAKNESS);
        player.removePotionEffect(PotionEffectType.HUNGER);
        player.removePotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE);
    }
    public boolean PlayerInGlitchedChunk(Player player) {
        Chunk chunk = player.getLocation().getChunk();
        return isGlitchedChunk(chunk);
    }
    public boolean isGlitchedChunk(Chunk chunk) {
        return chunk.getPersistentDataContainer().has(
                CHUNK_TYPE,
                PersistentDataType.INTEGER
        );
    }

}
