package net.twinges.staff.commands;

import net.frozenorb.qlib.command.Command;
import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.entity.Player;

public class VanishCommand {


    @Command(names = {"v", "vanish"}, permission = "tstaff.vanish")
    public static void vanishCommand(Player player) {
        if (!jStaff.getInstance().getStaffModeHandler().isVanished(player)) {
            player.sendMessage(CC.translate("&aEnabled vanish."));
            jStaff.getInstance().getStaffModeHandler().setVanished(player);
        } else {
            player.sendMessage(CC.translate("&cDisabled vanish."));
            jStaff.getInstance().getStaffModeHandler().removeVanish(player);
        }
    }
}
