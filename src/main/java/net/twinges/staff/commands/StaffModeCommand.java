package net.twinges.staff.commands;

import net.frozenorb.qlib.command.Command;
import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class StaffModeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
                jStaff.getInstance().getStaffModeHandler().removeStaffMode(player);
                player.sendMessage(CC.translate("&cYou have disabled mod mode."));
            } else {
                jStaff.getInstance().getStaffModeHandler().putInStaffMode(player);
                player.sendMessage(CC.translate("&aYou have enabled mod mode."));
            }
        } else {
            sender.sendMessage(CC.translate("&cYou must be a player to execute this command."));
        }



        return false;
    }
}
