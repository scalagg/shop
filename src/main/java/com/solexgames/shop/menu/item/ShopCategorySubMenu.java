package com.solexgames.shop.menu.item;

import com.solexgames.core.util.builder.ItemBuilder;
import com.solexgames.core.util.external.Button;
import com.solexgames.core.util.external.Menu;
import com.solexgames.shop.Shop;
import com.solexgames.shop.category.ShopCategory;
import com.solexgames.shop.purchasable.ShopPurchasableItem;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@RequiredArgsConstructor
public class ShopCategorySubMenu extends Menu {

    private final ShopCategory category;

    @Override
    public String getTitle(Player player) {
        return "Shop » " + this.category.getName();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttonMap = new HashMap<>();
        final AtomicInteger atomicInteger = new AtomicInteger();
        final List<ShopPurchasableItem> itemList = Shop.getInstance().getItemHandler()
                .fetchItemsByCategory(this.category);

        itemList.forEach(shopPurchasableItem -> {
            final List<String> finalDescription = new ArrayList<>(shopPurchasableItem.getDescription());

            finalDescription.add(" ");
            finalDescription.add(ChatColor.GRAY + "Price: " + ChatColor.WHITE + shopPurchasableItem.getPrice() + " XP");
            finalDescription.add(" ");
            finalDescription.add(shopPurchasableItem.canPurchase(player) ? ChatColor.YELLOW + "[Click to purchase item]" : ChatColor.RED + "[You don't have enough XP]");

            buttonMap.put(atomicInteger.getAndIncrement(), new ItemBuilder(shopPurchasableItem.getMaterial())
                    .setDisplayName(ChatColor.GREEN + shopPurchasableItem.getName())
                    .addLore(finalDescription)
                    .toButton((player1, clickType) -> Shop.getInstance().getShopHandler().attemptToPurchase(player, shopPurchasableItem))
            );
        });

        return buttonMap;
    }
}
