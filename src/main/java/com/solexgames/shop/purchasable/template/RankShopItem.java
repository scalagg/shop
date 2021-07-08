package com.solexgames.shop.purchasable.template;

import com.solexgames.core.player.ranks.Rank;
import com.solexgames.shop.purchasable.ShopPurchasableItem;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

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
        ), Arrays.asList(rank.getName(), "14d"), "EMERALD", 1000);
    }
}
