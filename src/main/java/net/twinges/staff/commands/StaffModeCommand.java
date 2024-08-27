package net.twinges.staff.commands;

import net.frozenorb.qlib.command.Command;
import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.entity.Player;


public class StaffModeCommand {

    @Command(names = {"staff", "staffmode", "mod", "h"}, permission = "tstaff.staff")
    public static void staffModeCommand(Player player) {
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            jStaff.getInstance().getStaffModeHandler().removeStaffMode(player);
            player.sendMessage(CC.translate("&cYou have disabled mod mode."));
        } else {
            jStaff.getInstance().getStaffModeHandler().putInStaffMode(player);
            player.sendMessage(CC.translate("&aYou have enabled mod mode."));
        }
    }
}
