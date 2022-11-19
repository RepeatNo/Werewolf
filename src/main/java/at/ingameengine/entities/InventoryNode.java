package at.ingameengine.entities;

import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryNode {

    private Inventory inventory;
    private int size;
    private InventoryNode parent;
    private HashMap<String, InventoryNode> children;

    public InventoryNode(Inventory inventory, InventoryNode parent, HashMap<String, InventoryNode> children) {
        this.inventory = inventory;
        this.parent = parent;
        this.children = children;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public InventoryNode getParent() {
        return parent;
    }

    public HashMap<String, InventoryNode> getChildren() {
        return children;
    }

    public void addChild(String displayName,InventoryNode child) {
        children.put(displayName, child);
    }
}
