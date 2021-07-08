package com.solexgames.shop.handler;

import com.solexgames.shop.Shop;
import com.solexgames.shop.category.ShopCategory;
import com.solexgames.shop.purchasable.ShopPurchasableItem;
import com.solexgames.shop.settings.ShopSettings;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@Getter
public class ItemHandler {

    private final List<ShopPurchasableItem> items = new ArrayList<>();

    public void loadFromConfig() {
        final ShopSettings shopSettings = Shop.getInstance().getShopSettings();

        this.items.addAll(shopSettings.getShopItems());
    }

    public List<ShopPurchasableItem> fetchItemsByCategory(ShopCategory category) {
        return this.items.stream()
                .filter(item -> item.getCategoryName().equalsIgnoreCase(category.getName()))
                .collect(Collectors.toList());
    }
}
