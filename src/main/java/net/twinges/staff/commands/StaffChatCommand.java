package net.twinges.staff.commands;

import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cYou must be a player to execute this command!"));
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("tstaff.staff")) {
            player.sendMessage(CC.translate("&cNo permission."));
            return false;
        }
        if (!jStaff.getInstance().getStaffModeHandler().isInStaffChat(player)) {
            jStaff.getInstance().getStaffModeHandler().putInStaffChat(player);
            player.sendMessage(CC.translate("&eStaff Chat: &aEnabled"));
        } else {
            jStaff.getInstance().getStaffModeHandler().removeStaffChat(player);
            player.sendMessage(CC.translate("&eStaff Chat: &cDisabled"));
        }






        return false;
    }
}
