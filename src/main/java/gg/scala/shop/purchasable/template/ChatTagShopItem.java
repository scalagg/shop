package gg.scala.shop.purchasable.template;

import gg.scala.chattags.plugin.model.ChatTag;
import gg.scala.shop.purchasable.ShopPurchasableItem;
import org.bukkit.ChatColor;

import java.util.Collections;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class ChatTagShopItem extends ShopPurchasableItem {

    public ChatTagShopItem(ChatTag prefix, int price) {
        super(ChatColor.GREEN + prefix.getDisplayName() + " " + ChatColor.GRAY + "(" + prefix.getLegacy() + ChatColor.GRAY + ")", "Chat Tags", "Chat Tags", Collections.singletonList(
                ChatColor.GRAY + "Purchase the " + prefix.getDisplayName() + " chat tag!"
        ), Collections.singletonList(prefix.getDisplayName()), "NAME_TAG", price);
    }
}
