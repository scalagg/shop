package com.solexgames.shop.purchasable.template;

import com.solexgames.core.player.prefixes.Prefix;
import com.solexgames.core.player.ranks.Rank;
import com.solexgames.shop.purchasable.ShopPurchasableItem;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class PrefixShopItem extends ShopPurchasableItem {

    public PrefixShopItem(Prefix prefix, int price) {
        super(ChatColor.GREEN + prefix.getName() + " " + ChatColor.GRAY + "(" + prefix.getPrefix() + ChatColor.GRAY + ")", "Prefixes", "Prefix", Collections.singletonList(
                ChatColor.GRAY + "Purchase the " + prefix.getName() + " prefix!"
        ), Collections.singletonList(prefix.getName()), "ITEM_FRAME", price);
    }
}
