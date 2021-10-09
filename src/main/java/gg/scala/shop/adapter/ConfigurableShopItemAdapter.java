package gg.scala.shop.adapter;

import gg.scala.shop.purchasable.ShopPurchasableItem;
import net.evilblock.cubed.serializers.Serializers;
import org.bukkit.configuration.ConfigurationSection;
import xyz.mkotb.configapi.internal.adapt.ObjectAdapter;

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
        return Serializers.getGson().toJson(configurableShopCategory);
    }
}
