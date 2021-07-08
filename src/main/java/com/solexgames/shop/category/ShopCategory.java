package com.solexgames.shop.category;

import org.bukkit.Material;

import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public abstract class ShopCategory {

    public abstract String getName();

    public abstract Material getMaterial();

    public abstract List<String> getDescription();

}
