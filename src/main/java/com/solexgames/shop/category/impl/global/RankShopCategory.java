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

public class RankShopCategory extends ShopCategory {

    @Override
    public String getName() {
        return "Ranks";
    }

    @Override
    public Material getMaterial() {
        return Material.BED;
    }

    @Override
    public List<String> getDescription() {
        return new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "Purchase ranks for up to 14 days",
                ChatColor.GRAY + "with your experience!"
        ));
    }
}
