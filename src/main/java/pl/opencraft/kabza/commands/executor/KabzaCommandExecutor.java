package pl.opencraft.kabza.commands.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import pl.opencraft.kabza.commands.base.CmdNode;
import pl.opencraft.kabza.commands.base.CmdRoot;
import pl.opencraft.kabza.commands.methods.GiveBag;
import pl.opencraft.kabza.commands.methods.InspectBag;
import pl.opencraft.kabza.commands.methods.Reload;
import pl.opencraft.kabza.commands.methods.bagtype.*;

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
                    new CmdNode("<bag_type_id>").subCmds(
                            new CmdNode("create").setMethod(new CreateBagType()),
                            new CmdNode("inspect").subCmds(
                                    new CmdNode("all").setMethod(new InspectBagType()),
                                    new CmdNode("alloweditem").subCmds(
                                            new CmdNode("<item_type>").setMethod(new InspectAllowedItem())
                                    )
                            ),
                            new CmdNode("edit").subCmds(
                                    new CmdNode("set").subCmds(
                                            new CmdNode("bagname").subCmds(
                                                    new CmdNode("<bag_name>").setMethod(new SetBagName())
                                            ),
                                            new CmdNode("bagitemtype").subCmds(
                                                    new CmdNode("<item_type>").setMethod(new SetBagItemType())
                                            ),
                                            new CmdNode("craftingenabled").subCmds(
                                                    new CmdNode("<flag>").setMethod(new SetCraftingEnabled())
                                            ),
                                            new CmdNode("shapelesscrafting").subCmds(
                                                    new CmdNode("<flag>").setMethod(new SetShapelessCrafting())
                                            ),
                                            new CmdNode("allitemsallowed").subCmds(
                                                    new CmdNode("<flag>").setMethod(new SetAllItemsAllowed())
                                            )
                                    ),
                                    new CmdNode("edit").subCmds(
                                            new CmdNode("bagdescription").subCmds(
                                                    new CmdNode("add").subCmds(
                                                            new CmdNode("<line_no>").subCmds(
                                                                    new CmdNode("<line>").setMethod(new AddBagDescriptionLine())
                                                            )
                                                    ),
                                                    new CmdNode("remove").subCmds(
                                                            new CmdNode("<line_no>").setMethod(new RemoveBagDescriptionLine())
                                                    ),
                                                    new CmdNode("clear").setMethod(new ClearBagDescription())
                                            ),
                                            new CmdNode("alloweditem").subCmds(
                                                    new CmdNode("<item_type>").subCmds(
                                                            new CmdNode("set").subCmds(
                                                                    new CmdNode("allnamesallowed").subCmds(
                                                                            new CmdNode("<flag>").setMethod(new SetAllowedItemAllNamesAllowed())
                                                                    ),
                                                                    new CmdNode("allloresallowed").subCmds(
                                                                            new CmdNode("<flag>").setMethod(new SetAllowedItemAllLoresAllowed())
                                                                    ),
                                                                    new CmdNode("nonameallowed").subCmds(
                                                                            new CmdNode("<flag>").setMethod(new SetAllowedItemNoNameAllowed())
                                                                    ),
                                                                    new CmdNode("noloreallowed").subCmds(
                                                                            new CmdNode("<flag>").setMethod(new SetAllowedItemNoLoreAllowed())
                                                                    )
                                                            ),
                                                            new CmdNode("edit").subCmds(
                                                                    new CmdNode("names").subCmds(
                                                                            new CmdNode("add").subCmds(
                                                                                    new CmdNode("<name>").setMethod(new AddAllowedItemName())
                                                                            ),
                                                                            new CmdNode("remove").subCmds(
                                                                                    new CmdNode("<name_no>").setMethod(new RemoveAllowedItemName())
                                                                            ),
                                                                            new CmdNode("clear").setMethod(new ClearAllowedItemNames())
                                                                    ),
                                                                    new CmdNode("lores").subCmds(
                                                                            new CmdNode("<lore_no>").subCmds(
                                                                                    new CmdNode("add").subCmds(
                                                                                            new CmdNode("<line_no>").subCmds(
                                                                                                    new CmdNode("<line>").setMethod(new AddAllowedItemLoreLine())
                                                                                            )
                                                                                    ),
                                                                                    new CmdNode("remove").subCmds(
                                                                                            new CmdNode("all").setMethod(new RemoveAllowedItemLore()),
                                                                                            new CmdNode("<line_no>").setMethod(new RemoveAllowedItemLoreLine())
                                                                                    )
                                                                            ),
                                                                            new CmdNode("clear").setMethod(new ClearAllowedItemLores())
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
