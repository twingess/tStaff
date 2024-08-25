package net.twinges.staff.commands;

import net.frozenorb.qlib.command.Command;
import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class StaffModeCommand {

    @Command(names = {"staff", "staffmode", "mod", "h"}, permission = "tstaff.staff")
    public static void staffModeCommand(Player player) {
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            jStaff.getInstance().getStaffModeHandler().removeStaffMode(player);
            CC.translate("&cYou have disabled mod mode.");
        } else {
            jStaff.getInstance().getStaffModeHandler().putInStaffMode(player);
            jStaff.getInstance().getStaffModeHandler().setVanished(player);
        }

    }
}
