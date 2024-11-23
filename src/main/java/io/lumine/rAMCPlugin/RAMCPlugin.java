package io.lumine.rAMCPlugin;

import io.lumine.rAMCPlugin.commands.LavaDecor;
import io.lumine.rAMCPlugin.commands.LavaDecorTabCompletion;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public final class RAMCPlugin extends JavaPlugin {
    private static RAMCPlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;
        Logger log = this.getLogger();

        getServer().getPluginManager().registerEvents(new LavaBucketCustom(), this);
        getServer().getPluginManager().registerEvents(new GetBucketBack(), this);
        getCommand("lavadecor").setExecutor(new LavaDecor());
        getCommand("lavadecor").setTabCompleter(new LavaDecorTabCompletion());
        log.info("RAMC Custom Plugin is loaded! That's alot of nuts!");
    }

    public static RAMCPlugin getInstance(){
        return instance;
    }
}