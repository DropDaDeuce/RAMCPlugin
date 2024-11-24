package io.lumine.rAMCPlugin;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class StopPhysicsEvent implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerBucketEmptyEvent event) {
        if (event.getBlock().getType() == Material.BROWN_MUSHROOM_BLOCK){
            event.setCancelled(true);
        }
    }
}
