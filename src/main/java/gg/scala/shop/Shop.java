package gg.scala.shop;

import gg.scala.chattags.plugin.handler.ChatTagsService;
import gg.scala.commons.ExtendedScalaPlugin;
import gg.scala.commons.annotations.container.ContainerEnable;
import gg.scala.lemon.handler.RankHandler;
import gg.scala.shop.adapter.ConfigurableShopCategoryAdapter;
import gg.scala.shop.adapter.ConfigurableShopItemAdapter;
import gg.scala.shop.category.impl.ConfigurableShopCategory;
import gg.scala.shop.category.impl.global.ChatTagShopCategory;
import gg.scala.shop.category.impl.global.RankShopCategory;
import gg.scala.shop.currency.CurrencyHandler;
import gg.scala.shop.currency.impl.GrapeCurrencyHandler;
import gg.scala.shop.handler.CategoryHandler;
import gg.scala.shop.handler.DataTypeHandler;
import gg.scala.shop.handler.ItemHandler;
import gg.scala.shop.handler.ShopHandler;
import gg.scala.shop.purchasable.ShopPurchasableItem;
import gg.scala.shop.purchasable.template.ChatTagShopItem;
import gg.scala.shop.purchasable.template.RankShopItem;
import gg.scala.shop.settings.ShopSettings;
import lombok.Getter;
import lombok.Setter;
import xyz.mkotb.configapi.ConfigFactory;
import xyz.mkotb.configapi.internal.adapt.AdapterHandler;

import java.util.Arrays;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@Getter
public final class Shop extends ExtendedScalaPlugin {

    @Getter
    private static Shop instance;

    private ShopSettings shopSettings;

    private CategoryHandler categoryHandler;
    private ItemHandler itemHandler;
    private ShopHandler shopHandler;
    private DataTypeHandler dataTypeHandler;

    @Setter
    private CurrencyHandler currencyHandler;

    @ContainerEnable
    public void containerEnable() {
        instance = this;

        final ConfigFactory factory = ConfigFactory.newFactory(this);

        AdapterHandler.registerAdapter(ShopPurchasableItem.class, new ConfigurableShopItemAdapter());
        AdapterHandler.registerAdapter(ConfigurableShopCategory.class, new ConfigurableShopCategoryAdapter());

        this.shopSettings = factory.fromFile("settings", ShopSettings.class);

        this.categoryHandler = new CategoryHandler();
        this.itemHandler = new ItemHandler();
        this.shopHandler = new ShopHandler();
        this.dataTypeHandler = new DataTypeHandler();

        if (this.shopSettings.isShopEnabled()) {
            this.categoryHandler.loadFromConfig();
            this.itemHandler.loadFromConfig();
            this.dataTypeHandler.loadFromConfig();

            // temporary
            this.categoryHandler.getCategories().clear();
            this.categoryHandler.getCategories().addAll(Arrays.asList(
                    new RankShopCategory(),
                    new ChatTagShopCategory()
            ));

            this.itemHandler.getItems().clear();

            ChatTagsService.INSTANCE.getChatTags().forEach((uuid, chatTag) -> {
                this.itemHandler.getItems().add(new ChatTagShopItem(chatTag, 350));
            });

            this.currencyHandler = new GrapeCurrencyHandler();
        }
    }
}
