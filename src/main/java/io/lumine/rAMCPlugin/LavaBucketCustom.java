package io.lumine.rAMCPlugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Logger;

public class LavaBucketCustom implements Listener {
    private static final Logger log = Logger.getLogger("LavaBucketCustom");

    @EventHandler
    public void onPlayerInteract(PlayerBucketEmptyEvent event) {
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        Material m = event.getBucket();
        if (m == Material.LAVA_BUCKET) {
            if (itemStack.hasItemMeta()) {
                if (itemStack.getItemMeta().hasCustomModelData() && itemStack.getItemMeta().hasDisplayName()) {
                    Component itemDisplayName = itemStack.getItemMeta().displayName();
                    PlainTextComponentSerializer plainSerializer = PlainTextComponentSerializer.plainText();
                    if (itemDisplayName != null) {
                        String itemName = plainSerializer.serialize(itemDisplayName);
                        if (itemStack.getItemMeta().getCustomModelData() == 5220005) {
                            event.setCancelled(true); // Prevent default behavior
                            // Get the block the player is looking at
                            BlockFace targetFace = event.getBlockFace();
                            Block targetBlock = event.getBlockClicked();
                            Block faceBlock = targetBlock.getRelative(targetFace);

                            // Set the block state to a mushroom block with unused data
                            BlockState blockState = faceBlock.getState();
                            blockState.setType(Material.BROWN_MUSHROOM_BLOCK);
                            MultipleFacing multiFace = (MultipleFacing) blockState.getBlockData();

                            switch (itemName) {
                                case "§bLava Still":
                                    multiFace.setFace(BlockFace.NORTH, false);
                                    multiFace.setFace(BlockFace.EAST, false);
                                    multiFace.setFace(BlockFace.SOUTH, false);
                                    multiFace.setFace(BlockFace.WEST, false);
                                    multiFace.setFace(BlockFace.UP, false);
                                    multiFace.setFace(BlockFace.DOWN, true);
                                    break;
                                case "§bLava Flowing Up":
                                    multiFace.setFace(BlockFace.NORTH, false);
                                    multiFace.setFace(BlockFace.EAST, false);
                                    multiFace.setFace(BlockFace.SOUTH, false);
                                    multiFace.setFace(BlockFace.WEST, true);
                                    multiFace.setFace(BlockFace.UP, false);
                                    multiFace.setFace(BlockFace.DOWN, false);
                                    break;
                                case "§bLava Flowing Down":
                                    multiFace.setFace(BlockFace.NORTH, false);
                                    multiFace.setFace(BlockFace.EAST, true);
                                    multiFace.setFace(BlockFace.SOUTH, true);
                                    multiFace.setFace(BlockFace.WEST, false);
                                    multiFace.setFace(BlockFace.UP, false);
                                    multiFace.setFace(BlockFace.DOWN, false);
                                    break;
                                case "§bLava Flowing South-West":
                                    multiFace.setFace(BlockFace.NORTH, false);
                                    multiFace.setFace(BlockFace.EAST, false);
                                    multiFace.setFace(BlockFace.SOUTH, true);
                                    multiFace.setFace(BlockFace.WEST, false);
                                    multiFace.setFace(BlockFace.UP, false);
                                    multiFace.setFace(BlockFace.DOWN, false);
                                    break;
                                case "§bLava Flowing North-East":
                                    multiFace.setFace(BlockFace.NORTH, false);
                                    multiFace.setFace(BlockFace.EAST, false);
                                    multiFace.setFace(BlockFace.SOUTH, true);
                                    multiFace.setFace(BlockFace.WEST, true);
                                    multiFace.setFace(BlockFace.UP, false);
                                    multiFace.setFace(BlockFace.DOWN, false);
                                    break;
                            }

                            faceBlock.setBlockData(multiFace);
                            faceBlock.getState().update(false, false);

                            if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                                itemStack.setAmount(itemStack.getAmount() - 1);
                            }
                        }
                    }
                }
            }
        }
    }
}