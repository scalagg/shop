package com.solexgames.shop.handler;

import com.solexgames.shop.Shop;
import com.solexgames.shop.category.ShopCategory;
import com.solexgames.shop.settings.ShopSettings;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@Getter
public class CategoryHandler {

    private final List<ShopCategory> categories = new ArrayList<>();

    public void loadFromConfig() {
        final ShopSettings shopSettings = Shop.getInstance().getShopSettings();

        this.categories.addAll(shopSettings.getShopCategories());
    }
}
