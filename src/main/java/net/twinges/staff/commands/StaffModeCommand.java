package net.twinges.staff.commands;

import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class StaffModeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("You must be a player to execute this command!"));
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("tstaff.staff")) {
            player.sendMessage(CC.translate("&cNo permission."));
            return false;
        }


        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            jStaff.getInstance().getStaffModeHandler().removeStaffMode(player);
            player.sendMessage(CC.translate("&cYou have disabled mod mode."));
        } else {
            jStaff.getInstance().getStaffModeHandler().putInStaffMode(player);
            player.sendMessage(CC.translate("&aYou have enabled mod mode."));
        }


        return false;
    }
}
