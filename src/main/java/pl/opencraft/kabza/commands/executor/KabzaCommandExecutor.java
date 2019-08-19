package pl.opencraft.kabza.commands.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import pl.opencraft.kabza.commands.base.CmdNode;
import pl.opencraft.kabza.commands.base.CmdRoot;
import pl.opencraft.kabza.commands.methods.GiveBag;
import pl.opencraft.kabza.commands.methods.Reload;

import java.util.List;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class KabzaCommandExecutor implements CommandExecutor, TabCompleter {

    private CmdRoot kabza = new CmdRoot("kabza").subCmds(
            new CmdNode("give").subCmds(
                    new CmdNode("bag").subCmds(
                            new CmdNode("<bag_type_id>").setMethod(new GiveBag())
                    )
            ),
            new CmdNode("reload").setMethod(new Reload())
    );

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
