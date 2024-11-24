package io.lumine.rAMCPlugin;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import java.util.logging.Logger;

public class StopPhysicsEvent implements Listener {
    private static final Logger log = Logger.getLogger("LavaBucketCustom");

    @EventHandler
    public void onPlayerInteract(PlayerBucketEmptyEvent event) {
        if (event.getBlock().getType() == Material.BROWN_MUSHROOM_BLOCK){
            event.setCancelled(true);
        }
    }
}
