package gg.scala.shop.category.impl.configurable;

import gg.scala.shop.category.impl.ConfigurableShopCategory;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class KillEffectShopCategory extends ConfigurableShopCategory {

    public KillEffectShopCategory() {
        super("Kill Effects", "REDSTONE", new ArrayList<>(Arrays.asList(
                ChatColor.GRAY + "Purchase special death effects",
                ChatColor.GRAY + "to show off to your opponents!"
        )));
    }
}
