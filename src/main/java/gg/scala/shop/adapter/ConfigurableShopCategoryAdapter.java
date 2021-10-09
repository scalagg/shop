package gg.scala.shop.adapter;

import gg.scala.shop.category.impl.ConfigurableShopCategory;
import net.evilblock.cubed.serializers.Serializers;
import org.bukkit.configuration.ConfigurationSection;
import xyz.mkotb.configapi.internal.adapt.ObjectAdapter;

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
        return Serializers.getGson().toJson(configurableShopCategory);
    }
}
