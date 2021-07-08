package com.solexgames.shop.purchasable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@Getter
@RequiredArgsConstructor
public class ShopPurchasableItem {

    private final String name;
    private final String categoryName;

    private final List<String> description;
    private final List<String> data;

    private final Material material;
    private final int price;

}
