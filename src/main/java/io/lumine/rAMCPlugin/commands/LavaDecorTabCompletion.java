package io.lumine.rAMCPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Logger;

public class LavaDecorTabCompletion implements TabCompleter {
    private static final Logger log = Logger.getLogger("CustomLavaDecor");
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("still","flowup","flowdown","flow_s_w","flow_n_e");
        }
        return List.of();
    }
}