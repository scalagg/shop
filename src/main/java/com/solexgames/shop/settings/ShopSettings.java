package com.solexgames.shop.settings;

import com.google.common.collect.Lists;
import com.solexgames.lib.processor.config.comment.Comment;
import com.solexgames.shop.category.impl.ConfigurableShopCategory;
import com.solexgames.shop.category.impl.configurable.KillEffectShopCategory;
import com.solexgames.shop.category.impl.global.PrefixShopCategory;
import com.solexgames.shop.purchasable.ShopPurchasableItem;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author GrowlyX
 * @since 7/7/2021
 */

@Data
public class ShopSettings {

    @Comment("Should we enable the shop on this server?")
    private boolean shopEnabled = true;

    @Comment("Which items should we register?")
    private List<ShopPurchasableItem> shopItems = Lists.newArrayList();

    @Comment("Which categories should we register?")
    private List<ConfigurableShopCategory> shopCategories = Collections.singletonList(
            new KillEffectShopCategory()
    );

    @Comment("Which packages full of awesome data types should we register?")
    private List<String> dataTypes = Collections.singletonList(
            "com.solexgames.shop.purchasable.data.impl"
    );

}
