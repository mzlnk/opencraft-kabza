package pl.opencraft.kabza.commands.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import pl.opencraft.kabza.commands.base.CmdNode;
import pl.opencraft.kabza.commands.base.CmdRoot;
import pl.opencraft.kabza.commands.methods.CmdInMaintenance;
import pl.opencraft.kabza.commands.methods.GiveBag;
import pl.opencraft.kabza.commands.methods.InspectBag;
import pl.opencraft.kabza.commands.methods.bagtype.InspectBagType;
import pl.opencraft.kabza.commands.methods.Reload;

import java.util.List;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class KabzaCommandExecutor implements CommandExecutor, TabCompleter {

    private CmdRoot kabza = new CmdRoot("kabza").subCmds(
            new CmdNode("bag").subCmds(
                    new CmdNode("give").subCmds(
                            new CmdNode("<bag_type_id>").setMethod(new GiveBag())
                    ),
                    new CmdNode("inspect").setMethod(new InspectBag())
            ),
            new CmdNode("bagtype").subCmds(
                    new CmdNode("<bag_type_id>>").subCmds(
                            new CmdNode("create").setMethod(new CmdInMaintenance()),
                            new CmdNode("inspect").subCmds(
                                    new CmdNode("all").setMethod(new CmdInMaintenance()),
                                    new CmdNode("alloweditems").subCmds(
                                            new CmdNode("all").setMethod(new CmdInMaintenance()),
                                            new CmdNode("item").subCmds(
                                                    new CmdNode("<item_type>").setMethod(new CmdInMaintenance())
                                            )
                                    )
                            ),
                            new CmdNode("edit").subCmds(
                                    new CmdNode("set").subCmds(
                                            new CmdNode("bagname").subCmds(
                                                    new CmdNode("<bag_name>").setMethod(new CmdInMaintenance())
                                            ),
                                            new CmdNode("bagitemtype").subCmds(
                                                    new CmdNode("<item_type>").setMethod(new CmdInMaintenance())
                                            ),
                                            new CmdNode("craftingenabled").subCmds(
                                                    new CmdNode("<flag>").setMethod(new CmdInMaintenance())
                                            ),
                                            new CmdNode("shapelesscrafting").subCmds(
                                                    new CmdNode("<flag>").setMethod(new CmdInMaintenance())
                                            ),
                                            new CmdNode("allitemsallowed").subCmds(
                                                    new CmdNode("<flag>").setMethod(new CmdInMaintenance())
                                            )
                                    ),
                                    new CmdNode("edit").subCmds(
                                            new CmdNode("bagdescription").subCmds(
                                                    new CmdNode("add").subCmds(
                                                            new CmdNode("<line_no>").subCmds(
                                                                    new CmdNode("<line>").setMethod(new CmdInMaintenance())
                                                            )
                                                    ),
                                                    new CmdNode("remove").subCmds(
                                                            new CmdNode("<line_no>").setMethod(new CmdInMaintenance())
                                                    ),
                                                    new CmdNode("clear").setMethod(new CmdInMaintenance())
                                            ),
                                            new CmdNode("alloweditems").subCmds(
                                                    new CmdNode("<item_type>").subCmds(
                                                            new CmdNode("set").subCmds(
                                                                    new CmdNode("allnamesallowed").subCmds(
                                                                            new CmdNode("<flag>").setMethod(new CmdInMaintenance())
                                                                    ),
                                                                    new CmdNode("allloresallowed").subCmds(
                                                                            new CmdNode("<flag>").setMethod(new CmdInMaintenance())
                                                                    ),
                                                                    new CmdNode("nonamesallowed").subCmds(
                                                                            new CmdNode("<flag>").setMethod(new CmdInMaintenance())
                                                                    ),
                                                                    new CmdNode("noloresallowed").subCmds(
                                                                            new CmdNode("<flag>").setMethod(new CmdInMaintenance())
                                                                    )
                                                            ),
                                                            new CmdNode("edit").subCmds(
                                                                    new CmdNode("names").subCmds(
                                                                            new CmdNode("add").subCmds(
                                                                                    new CmdNode("<name>").setMethod(new CmdInMaintenance())
                                                                            ),
                                                                            new CmdNode("remove").subCmds(
                                                                                    new CmdNode("<line_no>").setMethod(new CmdInMaintenance())
                                                                            ),
                                                                            new CmdNode("clear").setMethod(new CmdInMaintenance())
                                                                    ),
                                                                    new CmdNode("lores").subCmds(
                                                                            new CmdNode("<lore_no>").subCmds(
                                                                                    new CmdNode("add").subCmds(
                                                                                            new CmdNode("<line_no>").subCmds(
                                                                                                    new CmdNode("<line>").setMethod(new CmdInMaintenance())
                                                                                            )
                                                                                    ),
                                                                                    new CmdNode("remove").subCmds(
                                                                                            new CmdNode("<line_no>").setMethod(new CmdInMaintenance())
                                                                                    ),
                                                                                    new CmdNode("clear").setMethod(new CmdInMaintenance())
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
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
