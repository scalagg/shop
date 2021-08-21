package com.solexgames.shop.menu.item;

import com.solexgames.core.util.builder.ItemBuilder;
import com.solexgames.core.util.external.Button;
import com.solexgames.core.util.external.Menu;
import com.solexgames.shop.ShopSpigotPlugin;
import com.solexgames.shop.category.ShopCategory;
import com.solexgames.shop.menu.ShopCategoryMenu;
import com.solexgames.shop.purchasable.ShopPurchasableItem;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
        final int currentAmount = ShopSpigotPlugin.getInstance().getCurrencyHandler().getAmount(player);

        return "Shop » " + this.category.getName() + " (" + ShopSpigotPlugin.getInstance().getCurrencyHandler().getId() + ": " + currentAmount + ")";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttonMap = new HashMap<>();
        final AtomicInteger atomicInteger = new AtomicInteger(10);
        final List<ShopPurchasableItem> itemList = ShopSpigotPlugin.getInstance().getItemHandler()
                .fetchItemsByCategory(this.category);

        itemList.forEach(shopPurchasableItem -> {
            final List<String> finalDescription = new ArrayList<>(shopPurchasableItem.getDescription());

            finalDescription.add(" ");
            finalDescription.add(ChatColor.GRAY + "Price: " + ChatColor.WHITE + shopPurchasableItem.getPrice() + " " + ShopSpigotPlugin.getInstance().getCurrencyHandler().getId());
            finalDescription.add(" ");
            finalDescription.add(shopPurchasableItem.canPurchase(player) ? ChatColor.YELLOW + "[Click to purchase item]" : ChatColor.RED + "[You don't have enough XP]");

            buttonMap.put(atomicInteger.getAndIncrement(), new ItemBuilder(shopPurchasableItem.getMaterial())
                    .setDisplayName(ChatColor.GREEN + shopPurchasableItem.getName())
                    .addLore(finalDescription)
                    .toButton((player1, clickType) -> ShopSpigotPlugin.getInstance().getShopHandler().attemptToPurchase(player, shopPurchasableItem))
            );
        });

        buttonMap.put(16, new ItemBuilder(Material.BED)
                .setDisplayName(ChatColor.RED + "Return to Main")
                .addLore(
                        "&7Return back to the",
                        "&7main shop menu.",
                        " ",
                        "&e[Click to open main]"
                )
                .toButton((player1, clickType) -> {
                    new ShopCategoryMenu().openMenu(player1);
                })
        );

        return buttonMap;
    }


    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public boolean isFillBorders() {
        return true;
    }
}
