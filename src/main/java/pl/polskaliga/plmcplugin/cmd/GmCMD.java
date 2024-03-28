package pl.polskaliga.plmcplugin.cmd;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.polskaliga.plmcplugin.PlmcPlugin;
import pl.polskaliga.plmcplugin.utils.ColorFixer;

public class GmCMD implements CommandExecutor {
    private final PlmcPlugin plugin;

    public GmCMD(PlmcPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("gm").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        } else {
            Player player = (Player)sender;
            String permissionMessage = this.plugin.getConfig().getString("no-permission");
            String prefix = this.plugin.getConfig().getString("prefix");
            if (!player.hasPermission(this.plugin.getConfig().getString("permissions.gm"))) {
                player.sendRawMessage(ColorFixer.addColors(prefix + permissionMessage));
                return false;
            } else {
                if (args.length == 1) {
                    String successMessage = this.plugin.getConfig().getString("messages.gm.success");
                    if (args[0].equalsIgnoreCase("0") | args[0].equalsIgnoreCase("survival")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendRawMessage(ColorFixer.addColors(prefix + successMessage));
                        return true;
                    }

                    if (args[0].equalsIgnoreCase("1") | args[0].equalsIgnoreCase("creative")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendRawMessage(ColorFixer.addColors(prefix + successMessage));
                        return true;
                    }

                    if (args[0].equalsIgnoreCase("2") | args[0].equalsIgnoreCase("adventure")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendRawMessage(ColorFixer.addColors(prefix + successMessage));
                        return true;
                    }

                    if (args[0].equalsIgnoreCase("3") | args[0].equalsIgnoreCase("spectator")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendRawMessage(ColorFixer.addColors(prefix + successMessage));
                        return true;
                    }
                }

                return false;
            }
        }
    }
}