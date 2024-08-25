package net.twinges.staff.listeners;

import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class StaffListeners implements Listener {



    @EventHandler
    public static void onBlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate("&cYou cannot break blocks while in staff mode."));
        }
    }

    @EventHandler
    public static void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate("&cYou cannot place blocks while in staff mode."));
        }
    }
}
