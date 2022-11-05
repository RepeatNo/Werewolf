package at.ingameengine.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class InventoryManager {

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

    public ItemStack getPlayerHead(String p, String name){
        boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        ItemStack item = new ItemStack(type,1);
        if(!isNewVersion) {
            item.setDurability((short) 3);
        }
        SkullMeta im = (SkullMeta) item.getItemMeta();
        im.setOwner(p);
        im.setDisplayName(name);
        item.setItemMeta(im);
        return item;
    }
    public ItemStack getPlayerHead(String p, String name , ArrayList<String> lore){
        boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        ItemStack item = new ItemStack(type,1);
        if(!isNewVersion) {
            item.setDurability((short) 3);
        }
        SkullMeta im = (SkullMeta) item.getItemMeta();
        im.setOwner(p);
        im.setDisplayName(name);
        im.setLore(lore);
        item.setItemMeta(im);
        return item;
    }

    public static ItemStack getCustomSkull(String url, String display) {

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (url.isEmpty()) return head;

        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", url));

        try {
            Method mtd = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            mtd.setAccessible(true);
            mtd.invoke(skullMeta, profile);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        skullMeta.setDisplayName(display);
        head.setItemMeta(skullMeta);
        return head;
    }
    public static ItemStack getCustomSkull(String url, String display, ArrayList<String> lore) {

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (url.isEmpty()) return head;

        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", url));

        try {
            Method mtd = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            mtd.setAccessible(true);
            mtd.invoke(skullMeta, profile);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        skullMeta.setDisplayName(display);
        skullMeta.setLore(lore);
        head.setItemMeta(skullMeta);
        return head;
    }
}
