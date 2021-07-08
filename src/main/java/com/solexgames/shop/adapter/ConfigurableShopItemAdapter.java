package com.solexgames.shop.adapter;

import com.solexgames.core.CorePlugin;
import com.solexgames.lib.processor.config.internal.adapt.ObjectAdapter;
import com.solexgames.shop.category.impl.ConfigurableShopCategory;
import com.solexgames.shop.purchasable.ShopPurchasableItem;
import org.bukkit.configuration.ConfigurationSection;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class ConfigurableShopItemAdapter implements ObjectAdapter<ShopPurchasableItem, String> {

    @Override
    public ShopPurchasableItem read(String key, ConfigurationSection configurationSection) {
        return new ShopPurchasableItem(
                configurationSection.getString(key + ".name"),
                configurationSection.getString(key + ".categoryName"),
                configurationSection.getString(key + ".dataType"),
                configurationSection.getStringList(key + ".description"),
                configurationSection.getStringList(key + ".data"),
                configurationSection.getString(key + ".material"),
                configurationSection.getInt(key + ".price")
        );
    }

    @Override
    public String write(ShopPurchasableItem configurableShopCategory) {
        return CorePlugin.GSON.toJson(configurableShopCategory);
    }
}
