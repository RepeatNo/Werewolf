package at.ingameengine.entities.inventory.button;

import at.ingameengine.entities.inventory.InventoryNode;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

public class OpenInvButton extends AInventoryButton {
    public OpenInvButton(Pair<Integer, Integer> position, String itemName, ItemStack itemStack) {
        super(position, itemName, itemStack);
    }

    @Override
    public void Execute(Werewolf plugin, InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryNode invNode = node.getNode(event.getCurrentItem().getItemMeta().getDisplayName());
        if (invNode == null) return;
        player.openInventory(invNode.getInventory());
    }
}
