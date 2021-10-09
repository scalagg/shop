package gg.scala.shop.purchasable.template;

import gg.scala.shop.purchasable.ShopPurchasableItem;
import gg.scala.peach.model.ChatTag;
import org.bukkit.ChatColor;

import java.util.Collections;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class ChatTagShopItem extends ShopPurchasableItem {

    public ChatTagShopItem(ChatTag prefix, int price) {
        super(ChatColor.GREEN + prefix.getDisplayName() + " " + ChatColor.GRAY + "(" + prefix.getValue() + ChatColor.GRAY + ")", "Prefixes", "Prefix", Collections.singletonList(
                ChatColor.GRAY + "Purchase the " + prefix.getDisplayName() + " prefix!"
        ), Collections.singletonList(prefix.getValue()), "ITEM_FRAME", price);
    }
}
