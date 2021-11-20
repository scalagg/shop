package gg.scala.shop.category.impl.global;

import gg.scala.shop.category.ShopCategory;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class ChatTagShopCategory extends ShopCategory {

    @Override
    public String getName() {
        return "Chat Tags";
    }

    @Override
    public Material getMaterial() {
        return Material.NAME_TAG;
    }

    @Override
    public List<String> getDescription() {
        return new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "Purchase permanent chat tags",
                ChatColor.GRAY + "with your coins!"
        ));
    }
}
