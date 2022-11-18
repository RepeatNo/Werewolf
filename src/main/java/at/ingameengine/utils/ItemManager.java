package at.ingameengine.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class ItemManager {

    public ItemStack getItem(Material material, int amount, String displayname)
    {
        ItemStack i = new ItemStack(material, amount);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayname);
        i.setItemMeta(im);
        return i;
    }

    public ItemStack getItem(Material material, int amount, String displayname, ArrayList<String> lore)
    {
        ItemStack i = new ItemStack(material, amount);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayname);
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack getHead(Player player, ArrayList<String> lore) {
        int lifePlayer = (int) player.getHealth();
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(player.getName());
        skull.setLore(lore);
        skull.setOwner(player.getName());
        item.setItemMeta(skull);
        return item;
    }
}
