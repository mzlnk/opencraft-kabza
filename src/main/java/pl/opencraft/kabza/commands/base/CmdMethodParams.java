package pl.opencraft.kabza.commands.base;

import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

@Setter
public class CmdMethodParams {

    public CommandSender sender = null;
    public Player player = null;

    public ItemStack item;

    public String bagTypeId;
    public String bagName;

    public int lineNo;
    public int nameNo;
    public int loreNo;
    public String line;
    public String name;

    public boolean flag;
    public int craftingSlot;

    public Material itemType;


    public CmdMethodParams(CommandSender sender) {
        this.sender = sender;
        if(sender instanceof Player) {
            this.player = (Player) sender;
            this.item = this.player.getInventory().getItemInMainHand();
        }
    }

}
