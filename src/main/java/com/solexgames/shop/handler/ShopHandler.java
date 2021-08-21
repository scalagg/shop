package com.solexgames.shop.handler;

import com.solexgames.shop.ShopSpigotPlugin;
import com.solexgames.shop.ShopSpigotConstants;
import com.solexgames.shop.purchasable.ShopPurchasableItem;
import com.solexgames.shop.purchasable.data.PurchasableDataType;
import com.solexgames.shop.purchasable.data.exception.InvalidDataException;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class ShopHandler {

    public void attemptToPurchase(Player player, ShopPurchasableItem item) {
        final int currentAmount = ShopSpigotPlugin.getInstance().getCurrencyHandler().getAmount(player);

        if (currentAmount < item.getPrice()) {
            player.sendMessage(ChatColor.RED + "You cannot afford this item! You need " + (item.getPrice() - currentAmount) + " more experience to purchase this item.");
            return;
        }

        final PurchasableDataType dataType = ShopSpigotPlugin.getInstance().getDataTypeHandler().findById(item.getDataType());

        if (dataType == null) {
            player.sendMessage(ChatColor.RED + "Something went terribly wrong while trying to purchase this product. (-1)");
            return;
        }

        try {
            dataType.applyToPlayer(player, item.getData());

            ShopSpigotPlugin.getInstance().getCurrencyHandler().remove(player, item.getPrice());

            player.sendMessage(ShopSpigotConstants.CHAT_PREFIX + "You've successfully purchased the " + ChatColor.GREEN + item.getName() + ChatColor.YELLOW + " item!");
        } catch (InvalidDataException dataException) {
            player.sendMessage(ChatColor.RED + "Error: " + dataException.getMessage());
        }
    }
}
