package net.daylight.test.ChunksEffects;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.concurrent.ThreadLocalRandom;

public class ChunkGlitches implements Listener {
    ChunksMain chunksMain;

    public ChunkGlitches(ChunksMain chunksMain) {
        this.chunksMain = chunksMain;
    }


    @EventHandler
    public void onCropGrow(BlockGrowEvent e) {
        if(!(chunksMain.isGlitchedChunk(e.getBlock().getChunk()))) return;
        if(!(ThreadLocalRandom.current().nextInt(2) == 1)) return;
        Block block = e.getBlock();
        BlockData data = block.getBlockData();

        if(data instanceof Ageable ageable) {
            ageable.setAge(ThreadLocalRandom.current().nextInt(ageable.getMaximumAge()));
            block.setBlockData(ageable);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(!(chunksMain.isGlitchedChunk(e.getBlock().getChunk()))) return;
        if(!(ThreadLocalRandom.current().nextInt(3) == 1)) return;

        Block block = e.getBlock();
        BlockData data = block.getBlockData();
        if(data instanceof Ageable ageable) {
            ageable.setAge(ThreadLocalRandom.current().nextInt(ageable.getMaximumAge()));
            block.setBlockData(ageable);
        }
    }
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player player)) return;
        if(!(chunksMain.PlayerInGlitchedChunk(player))) return;
        if (e.getCause() == EntityDamageEvent.DamageCause.CUSTOM) return;
        if((ThreadLocalRandom.current().nextInt(5) == 1)) {
            e.setCancelled(true);
        }
        if((ThreadLocalRandom.current().nextInt(5) == 1)) {
            player.playSound(player.getLocation(),
                    Sound.ENTITY_PLAYER_HURT, 1f, 0.3f);
            float glitch = ThreadLocalRandom.current().nextFloat() * 360F;
            player.playHurtAnimation(glitch);
            player.damage(1);
        }
    }
}
