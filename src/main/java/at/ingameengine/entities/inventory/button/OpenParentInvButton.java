package at.ingameengine.entities.inventory.button;

import at.ingameengine.entities.inventory.InventoryNode;
import at.ingameengine.utils.ItemManager;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.javatuples.Pair;

public class OpenParentInvButton extends AInventoryButton {
    public OpenParentInvButton(Pair<Integer, Integer> position) {
        super(position,
                new ItemManager().getInstance().getInstance().getSkullConfigHead("§cBack", "basic.back", null));
    }

    @Override
    public void Execute(Werewolf plugin, InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryNode invNode = node.getNode(event.getInventory());
        if (invNode == null) return;
        player.openInventory(invNode.getParent().getInventory());
    }
}
