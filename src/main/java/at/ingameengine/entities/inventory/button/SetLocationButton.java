package at.ingameengine.entities.inventory.button;

import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

public class SetLocationButton extends AInventoryButton {

    Werewolf plugin;

    public SetLocationButton(Pair<Integer, Integer> position, String locationName, String itemName, ItemStack itemStack, Werewolf plugin) {
        super(position, itemName, itemStack);
        this.plugin = plugin;
    }

    @Override
    public void Execute(InventoryClickEvent event) {
        Location location = event.getWhoClicked().getLocation();

        plugin.getLocationsManager().setLocation(itemName, location);
    }
}
