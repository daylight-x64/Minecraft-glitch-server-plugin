package net.daylight.test.ChunksEffects;

import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ChunksPlayerHandler implements Listener {

    ChunksMain chunksMain;

    public ChunksPlayerHandler(ChunksMain chunksMain) {
        this.chunksMain = chunksMain;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        if(!(e.getTo().getChunk().equals(e.getFrom().getChunk()))) {
            Chunk chunk = e.getTo().getChunk();
            PersistentDataContainer pdc = chunk.getPersistentDataContainer();

            if(pdc.has(ChunksMain.CHUNK_TYPE, PersistentDataType.INTEGER)) {
                chunksMain.playerInSpecialChunk(e.getPlayer());
            } else {
                chunksMain.playerNotInSpecialChunk(e.getPlayer());
            }
        }
    }
}
