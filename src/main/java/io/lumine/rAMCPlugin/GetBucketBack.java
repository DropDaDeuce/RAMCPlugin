package io.lumine.rAMCPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

public class GetBucketBack implements Listener {
    private static final Logger log = Logger.getLogger("GetBucketBack");
    @EventHandler
    public void onBlockBreaks(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        if (block.getType() == Material.BROWN_MUSHROOM_BLOCK) {
            BlockState blockState = block.getState();
            MultipleFacing multiFace = (MultipleFacing) blockState.getBlockData();
            String bucketName = getString(multiFace);
            if (bucketName != null) {
                e.setCancelled(true);
                ItemStack lavaBucket = new ItemStack(Material.LAVA_BUCKET);
                ItemMeta meta = lavaBucket.getItemMeta();
                meta.setCustomModelData(5220005);
                meta.setMaxStackSize(64);
                meta.displayName(Component.text(ChatColor.AQUA + bucketName));
                meta.displayName().decoration(TextDecoration.ITALIC, false);
                meta.setEnchantmentGlintOverride(true);

                lavaBucket.setItemMeta(meta);
                block.setType(Material.AIR);
                block.getWorld().dropItemNaturally(block.getLocation(), lavaBucket);
            }
        }
    }

    private static @Nullable String getString(MultipleFacing multiFace) {
        boolean north = false, south = false, east = false, west = false, up = false, down = false;

        for (BlockFace face: multiFace.getFaces()){
            switch (face.name()) {
                case "NORTH":
                    north = true;
                    break;
                case "SOUTH":
                    south = true;
                    break;
                case "EAST":
                    east = true;
                    break;
                case "WEST":
                    west = true;
                    break;
                case "UP":
                    up = true;
                    break;
                case "DOWN":
                    down = true;
                    break;
            }
        }
        String bucketName = null;

        if (down && !up && !north && !south && !west && !east) {
            bucketName = "Lava Still";
        } else if (!down && !up && !north && !south && west && !east) {
            bucketName = "Lava Flowing Up";
        } else if (!down && !up && !north && south && !west && !east) {
            bucketName = "Lava Flowing South-West";
        }else if (!down && !up && !north && south && west && !east) {
            bucketName = "Lava Flowing North-East";
        }else if (!down && !up && !north && south && !west && east) {
            bucketName = "Lava Flowing Down";
        }
        return bucketName;
    }
}
