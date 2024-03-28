package pl.polskaliga.plmcplugin.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.polskaliga.plmcplugin.PlmcPlugin;
import pl.polskaliga.plmcplugin.utils.ColorFixer;

public class FlyCMD implements CommandExecutor {
    private final PlmcPlugin plugin;

    public FlyCMD(PlmcPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("fly").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        } else {
            Player player = (Player)sender;
            String permissionMessage = this.plugin.getConfig().getString("no-permission");
            String prefix = this.plugin.getConfig().getString("prefix");
            if (!player.hasPermission(this.plugin.getConfig().getString("permissions.fly"))) {
                player.sendRawMessage(ColorFixer.addColors(prefix + permissionMessage));
                return false;
            } else {
                String offlineMessage;
                if (args.length == 0) {
                    String enabledMessage = this.plugin.getConfig().getString("messages.fly.enabled");
                    offlineMessage = this.plugin.getConfig().getString("messages.fly.disabled");
                    if (!player.getAllowFlight()) {
                        player.sendRawMessage(ColorFixer.addColors(prefix + enabledMessage));
                        player.setAllowFlight(true);
                    } else {
                        player.sendRawMessage(ColorFixer.addColors(prefix + offlineMessage));
                        player.setAllowFlight(false);
                    }

                    return true;
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    offlineMessage = this.plugin.getConfig().getString("messages.fly.player-offline");
                    if (target == null) {
                        player.sendRawMessage(ColorFixer.addColors(prefix + offlineMessage));
                        return false;
                    } else {
                        String enabledMessage = this.plugin.getConfig().getString("messages.fly.enabled-player");
                        String disabledMessage = this.plugin.getConfig().getString("messages.fly.disabled-player");
                        enabledMessage = enabledMessage.replace("{player}", target.getName());
                        disabledMessage = disabledMessage.replace("{player}", target.getName());
                        if (!target.getAllowFlight()) {
                            target.sendRawMessage(ColorFixer.addColors(prefix + this.plugin.getConfig().getString("messages.fly.enabled")));
                            player.sendRawMessage(ColorFixer.addColors(prefix + enabledMessage));
                            target.setAllowFlight(true);
                        } else {
                            target.sendRawMessage(ColorFixer.addColors(prefix + this.plugin.getConfig().getString("messages.fly.disabled")));
                            player.sendRawMessage(ColorFixer.addColors(prefix + disabledMessage));
                            target.setAllowFlight(false);
                        }

                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
    }
}