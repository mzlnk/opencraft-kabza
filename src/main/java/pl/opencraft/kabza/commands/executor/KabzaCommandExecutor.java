package pl.opencraft.kabza.commands.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import pl.opencraft.kabza.commands.base.CmdRoot;

import java.util.List;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class KabzaCommandExecutor implements CommandExecutor, TabCompleter {

    private CmdRoot kabza = null; // todo: code here

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        kabza.onCommand(sender, label, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String args[]) {
        return kabza.onTabComplete(args);
    }

}
