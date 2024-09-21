package net.twinges.staff.commands;

import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cYou must be a player to run this command."));
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("tstaff.vanish")) {
            player.sendMessage(CC.translate("&cNo permission."));
            return false;
        }

        if (jStaff.getInstance().getStaffModeHandler().isVanished(player)) {
            player.sendMessage(CC.translate("&cDisabled Vanish."));
            jStaff.getInstance().getStaffModeHandler().removeVanish(player);
        } else {
            player.sendMessage(CC.translate("&aEnabled Vanish."));
            jStaff.getInstance().getStaffModeHandler().setVanished(player);
        }

        return false;
    }
}
