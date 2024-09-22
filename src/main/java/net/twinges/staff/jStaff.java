package net.twinges.staff;

import lombok.Getter;
import net.twinges.staff.commands.*;
import net.twinges.staff.handlers.ItemHandler;
import net.twinges.staff.handlers.StaffModeHandler;
import net.twinges.staff.listeners.ItemListeners;
import net.twinges.staff.listeners.StaffListeners;
import net.twinges.staff.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class jStaff extends JavaPlugin {

    @Getter public static jStaff instance;
    @Getter private StaffModeHandler staffModeHandler;
    @Getter private ItemHandler itemHandler;

    @Override
    public void onEnable() {
        instance = this;
        registerHandlers();
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerHandlers() {
        staffModeHandler = new StaffModeHandler(this);
        itemHandler = new ItemHandler();
    }
    private void registerListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new StaffListeners(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemListeners(), this);
    }

    private void registerCommands() {
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("staffmode").setExecutor(new StaffModeCommand());
        getCommand("buildmode").setExecutor(new BuildModeCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("staffbroadcast").setExecutor(new StaffBroadcastCommand());
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("unfreeze").setExecutor(new UnFreezeCommand());
    }
}