package net.twinges.staff;

import lombok.Getter;
import net.frozenorb.qlib.command.FrozenCommandHandler;
import net.twinges.staff.handlers.ItemHandler;
import net.twinges.staff.handlers.StaffModeHandler;
import net.twinges.staff.listeners.StaffListeners;
import net.twinges.staff.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class jStaff extends JavaPlugin {


    @Getter static jStaff instance;

    @Getter private StaffModeHandler staffModeHandler;
    @Getter private ItemHandler itemHandler;

    public void onEnable() {
        instance = this;
        registerHandlers();
        registerListeners();
        FrozenCommandHandler.registerAll(this);
        System.out.println(CC.translate("&6jStaff &ahas been enabled."));
    }


    public void onDisable() {
        System.out.println(CC.translate("&6jStaff &ahas been disabled."));
    }

    private void registerHandlers(){
        staffModeHandler = new StaffModeHandler(this);
        itemHandler = new ItemHandler();
    }
    private void registerListeners() {
        Bukkit.getServer().getPluginManager().registerEvents(new StaffListeners(), this);
    }
}