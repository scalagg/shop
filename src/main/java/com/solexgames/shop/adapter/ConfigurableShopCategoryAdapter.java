package com.solexgames.shop.adapter;

import com.solexgames.core.CorePlugin;
import com.solexgames.lib.processor.config.internal.adapt.ObjectAdapter;
import com.solexgames.shop.category.impl.ConfigurableShopCategory;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Date;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class ConfigurableShopCategoryAdapter implements ObjectAdapter<ConfigurableShopCategory, String> {

    @Override
    public ConfigurableShopCategory read(String key, ConfigurationSection configurationSection) {
        return new ConfigurableShopCategory(configurationSection.getString(key + ".name"), configurationSection.getString(key + ".material"), configurationSection.getStringList("description"));
    }

    @Override
    public String write(ConfigurableShopCategory configurableShopCategory) {
        return CorePlugin.GSON.toJson(configurableShopCategory);
    }
}
