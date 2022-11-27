package at.ingameengine.entities.inventory;

import at.ingameengine.entities.inventory.button.AInventoryButton;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

import java.util.ArrayList;

public class InventoryNode {

    private final String title;

    private Inventory inventory;
    private InventoryNode parent = null;
    private ArrayList<InventoryNode> children = new ArrayList<>();
    private ArrayList<AInventoryButton> invButtons = new ArrayList<>();

    public InventoryNode(String title, Inventory inventory, InventoryNode parent, ArrayList<InventoryNode> children, ArrayList<AInventoryButton> invButtons) {
        this.inventory = inventory;
        this.parent = parent;
        this.children = children;
        this.title = title;
        this.invButtons = invButtons;
    }

    public InventoryNode(String title, Inventory inventory, ArrayList<AInventoryButton> invButtons) {
        this.inventory = inventory;
        this.title = title;
        this.invButtons = invButtons;
    }

    public InventoryNode(String title) {
        this.title = title;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public InventoryNode getParent() {
        return parent;
    }

    public ArrayList<InventoryNode> getChildren() {
        return children;
    }

    public String getTitle() {
        return title;
    }

    public void addChild(InventoryNode child) {
        children.add(child);
        child.setParent(this);
    }

    public void addInvButton(AInventoryButton button) {
        button.SetNode(this);
        invButtons.add(button);
    }

    public ArrayList<AInventoryButton> getInvButtons() {
        return invButtons;
    }

    public AInventoryButton getInventoryButton(ItemStack itemStack) {
        for (AInventoryButton invButton : invButtons) {
            if (invButton.getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(itemStack.getItemMeta().getDisplayName())) {
                return invButton;
            }
        }
        return null;
    }

    private void setParent(InventoryNode inventoryNode) {
        this.parent = inventoryNode;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public InventoryNode getNode(String displayName) {
        if (title.equalsIgnoreCase(displayName)) {
            return this;
        }

        for (InventoryNode child : children) {
            if (child.getNode(displayName) != null) {
                return child.getNode(displayName);
            }
        }

        return null;
    }

    public InventoryNode getNode(Inventory inventory) {
        if (compare(inventory)) {
            return this;
        }

        for (InventoryNode child : children) {
            if (child.getNode(inventory) != null) {
                return child.getNode(inventory);
            }
        }

        return null;
    }

    public Boolean compare(Inventory compareInventory) {
        if (inventory.getSize() != compareInventory.getSize()) {
            return false;
        }

        if (inventory.getType() != compareInventory.getType()) {
            return false;
        }

        for (int i = 0; i < inventory.getSize(); i++) {
            if (ItemNameIsNotEqual(new Pair<>(inventory.getItem(i), compareInventory.getItem(i)))) {
                return false;
            }
        }

        return true;
    }

    private Boolean ItemNameIsNotEqual(Pair<ItemStack, ItemStack> itemStackPair) {
        if (itemStackPair.getValue0().getItemMeta() != null) return false;
        if (itemStackPair.getValue1().getItemMeta() != null) return false;
        return !itemStackPair.getValue0().getItemMeta().getDisplayName()
                .equalsIgnoreCase(itemStackPair.getValue1().getItemMeta().getDisplayName());
    }
}
