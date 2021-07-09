package com.solexgames.shop.category.impl.global;

import com.solexgames.shop.category.ShopCategory;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class PrefixShopCategory extends ShopCategory {

    @Override
    public String getName() {
        return "Prefixes";
    }

    @Override
    public Material getMaterial() {
        return Material.ITEM_FRAME;
    }

    @Override
    public List<String> getDescription() {
        return new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "Purchase permanent prefixes",
                ChatColor.GRAY + "with your experience!"
        ));
    }
}
