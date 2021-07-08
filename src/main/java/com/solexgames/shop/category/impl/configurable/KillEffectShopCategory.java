package com.solexgames.shop.category.impl.configurable;

import com.solexgames.shop.category.impl.ConfigurableShopCategory;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class KillEffectShopCategory extends ConfigurableShopCategory {

    public KillEffectShopCategory() {
        super("Kill Effects", "REDSTONE", new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "Purchase special kill effects",
                ChatColor.GRAY + "to show off to your opponents!"
        )));
    }
}
