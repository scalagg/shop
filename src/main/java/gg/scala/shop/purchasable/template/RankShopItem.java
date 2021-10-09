package gg.scala.shop.purchasable.template;

import gg.scala.shop.purchasable.ShopPurchasableItem;
import gg.scala.lemon.player.rank.Rank;
import org.bukkit.ChatColor;

import java.util.Arrays;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class RankShopItem extends ShopPurchasableItem {

    public RankShopItem(Rank rank, int price) {
        super(rank.getColor() + rank.getName() + " Rank", "Ranks", "Rank", Arrays.asList(
                ChatColor.GRAY + "Purchase the " + rank.getName() + " rank!",
                "",
                ChatColor.WHITE + " - " + ChatColor.GRAY + "14d duration"
        ), Arrays.asList(rank.getName(), "14d"), "EMERALD", price);
    }
}
