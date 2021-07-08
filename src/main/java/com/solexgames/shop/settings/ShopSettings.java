package com.solexgames.shop.settings;

import com.solexgames.lib.processor.config.comment.Comment;
import com.solexgames.shop.category.ShopCategory;
import com.solexgames.shop.category.impl.ConfigurableShopCategory;
import com.solexgames.shop.category.impl.configurable.KillEffectShopCategory;
import com.solexgames.shop.category.impl.global.RankShopCategory;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Comment("Which categories should we register?")
    private List<ConfigurableShopCategory> shopCategories = Collections.singletonList(
            new KillEffectShopCategory()
    );

}
