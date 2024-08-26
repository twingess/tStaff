package net.twinges.staff.listeners;

import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class StaffListeners implements Listener {



    @EventHandler
    public static void onBBE(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            if (jStaff.getInstance().getStaffModeHandler().hasBuildModeEnabled(player)) return;
            event.setCancelled(true);
            player.sendMessage(CC.translate("&cYou cannot break blocks while in staff mode."));
        }
    }

    @EventHandler
    public static void onBPE(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            if (jStaff.getInstance().getStaffModeHandler().hasBuildModeEnabled(player)) return;
            event.setCancelled(true);
            player.sendMessage(CC.translate("&cYou cannot place blocks while in staff mode."));
        }
    }

    @EventHandler
    public static void onICE(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.hasPermission("tstaff.admin")) return;
        if (event.getWhoClicked() instanceof Player) {
            if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
                if (jStaff.getInstance().getStaffModeHandler().hasBuildModeEnabled(player)) return;
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public static void onPGMCE(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            event.setCancelled(true);
        }
    }
}
