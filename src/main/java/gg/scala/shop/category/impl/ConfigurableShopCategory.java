package gg.scala.shop.category.impl;

import gg.scala.shop.category.ShopCategory;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@RequiredArgsConstructor
public class ConfigurableShopCategory extends ShopCategory {

    private final String name;
    private final String material;
    private final List<String> description;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Material getMaterial() {
        return Material.getMaterial(this.material);
    }

    @Override
    public List<String> getDescription() {
        return this.description;
    }
}
