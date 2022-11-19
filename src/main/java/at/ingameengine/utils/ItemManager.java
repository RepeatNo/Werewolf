package at.ingameengine.utils;

import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import com.mojang.authlib.GameProfile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ItemManager {

    public ItemStack getItem(Material material, int amount, String displayName)
    {
        ItemStack i = new ItemStack(material, amount);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayName);
        i.setItemMeta(im);
        return i;
    }

    public ItemStack getItem(Material material, int amount, String displayName, ArrayList<String> lore)
    {
        ItemStack i = new ItemStack(material, amount);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayName);
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }

    public ItemStack getHead(Player player,String displayName) {
        int lifePlayer = (int) player.getHealth();
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(displayName);
        skull.setOwner(player.getName());
        item.setItemMeta(skull);
        return item;
    }

    public ItemStack getHead(String displayName, String texture, ArrayList<String> lore) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta)playerHead.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));
        try {
            Method mtd = headMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            mtd.setAccessible(true);
            mtd.invoke(headMeta, profile);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        headMeta.setDisplayName(displayName);
        headMeta.setLore(lore);
        playerHead.setItemMeta(headMeta);
        return playerHead;
    }
}
