package io.lumine.rAMCPlugin.commands;

import io.lumine.rAMCPlugin.Msg;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LavaDecor implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        if (sender instanceof Player p) {
            if (args.length == 0) {
                Msg.send(p, "No type argument provided. Please choose a type!");
                Msg.send(p, "/lavadecor <bucket type> <amount to trade>");
                return true;
            } else if (args.length != 2) {
                Msg.send(p,"Incorrect number of arguments supplied. Expected '2' received '" + args.length + "'");
                Msg.send(p, "/lavadecor <bucket type> <amount to trade>");
                return true;
            } else {
                String arg = args[0];

                ItemStack lavaBucket = new ItemStack(Material.LAVA_BUCKET);
                ItemMeta meta = lavaBucket.getItemMeta();

                switch (arg) {
                    case "still":
                        arg = "Lava Still";
                        break;
                    case "flowup":
                        arg = "Lava Flowing Up";
                        break;
                    case "flowdown":
                        arg = "Lava Flowing Down";
                        break;
                    case "flow_s_w":
                        arg = "Lava Flowing South-West";
                        break;
                    case "flow_n_e":
                        arg = "Lava Flowing North-East";
                        break;
                    default:
                        Msg.send(p, arg + " is not a valid type.");
                        Msg.send(p, "/lavadecor <bucket type> <amount to trade>");
                        return true;
                }

                int qty;
                try {
                    qty = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    Msg.send(p, args[1] + " is not a valid number. Please enter the number of lava buckets you wish to trade.");
                    Msg.send(p, "/lavadecor <bucket type> <amount to trade>");
                    return true;
                }

                if (qty == 0) {
                    Msg.send(p, "No amount or 0 was entered. Please enter a valid amount greater than 0.");
                    Msg.send(p, "/lavadecor <bucket type> <amount to trade>");
                    return true;
                }

                meta.displayName(Component.text(ChatColor.AQUA + arg));
                meta.displayName().decoration(TextDecoration.ITALIC, false);

                int intBucket = 0;
                List<ItemStack> pBuckets = new ArrayList<>();

                for (ItemStack item : p.getInventory().getContents()) {
                    if (item != null && item.getType() == Material.LAVA_BUCKET) {
                        if (item.hasItemMeta()) {
                            if (!item.getItemMeta().hasCustomModelData()) {
                                intBucket++;
                                pBuckets.add(item);
                                if (intBucket == qty) {
                                    break;
                                }
                            }
                        } else {
                            intBucket++;
                            pBuckets.add(item);
                            if (intBucket == qty) {
                                break;
                            }
                        }
                    }
                }

                if (intBucket < qty) {
                    Msg.send(p, "Amount entered '" + qty + "' is greater that the amount of lava buckets you have '" + intBucket + "'");
                    Msg.send(p, "/lavadecor <bucket type> <amount to trade>");
                    return true;
                }

                meta.setCustomModelData(5220005);
                meta.setMaxStackSize(64);
                meta.setEnchantmentGlintOverride(true);
                lavaBucket.setItemMeta(meta);

                int iRemoved = 0;
                for (ItemStack bucket: pBuckets) {
                    if (iRemoved >= qty) {
                        break;
                    }
                    p.getInventory().removeItem(bucket);
                    p.getInventory().addItem(lavaBucket.clone());
                }

                Msg.send(p, "Here you go!");
                return true;
            }

        }

        Msg.send(sender, "Only players can run this command!");
        return true;
    }
}