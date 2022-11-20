package at.ingameengine.entities;

import at.ingameengine.utils.InventoryBuilder;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;

public class InventoryNode {

    private final Inventory inventory;
    private final String title;
    private InventoryNode parent = null;
    private HashSet<InventoryNode> children = new HashSet<>();

    public InventoryNode(String title, Inventory inventory, InventoryNode parent, HashSet<InventoryNode> children) {
        this.inventory = inventory;
        this.parent = parent;
        this.children = children;
        this.title = title;
    }

    public InventoryNode(String title, Inventory inventory) {
        this.inventory = inventory;
        this.title = title;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public InventoryNode getParent() {
        return parent;
    }

    public HashSet<InventoryNode> getChildren() {
        return children;
    }

    public void addChild(InventoryNode child) {
        children.add(child);
        child.setParent(this);
    }

    private void setParent(InventoryNode inventoryNode) {
        this.parent = inventoryNode;
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
        if (InventoryBuilder.compareInventory(this.inventory, inventory)) {
            return this;
        }

        for (InventoryNode child : children) {
            if (child.getNode(inventory) != null) {
                return child.getNode(inventory);
            }
        }

        return null;
    }
}
