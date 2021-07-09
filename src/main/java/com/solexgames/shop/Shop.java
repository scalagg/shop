package com.solexgames.shop;

import com.solexgames.core.player.prefixes.Prefix;
import com.solexgames.core.player.ranks.Rank;
import com.solexgames.lib.processor.config.ConfigFactory;
import com.solexgames.lib.processor.config.internal.adapt.AdapterHandler;
import com.solexgames.shop.adapter.ConfigurableShopCategoryAdapter;
import com.solexgames.shop.adapter.ConfigurableShopItemAdapter;
import com.solexgames.shop.category.impl.ConfigurableShopCategory;
import com.solexgames.shop.category.impl.configurable.KillEffectShopCategory;
import com.solexgames.shop.category.impl.global.PrefixShopCategory;
import com.solexgames.shop.category.impl.global.RankShopCategory;
import com.solexgames.shop.handler.CategoryHandler;
import com.solexgames.shop.handler.DataTypeHandler;
import com.solexgames.shop.handler.ItemHandler;
import com.solexgames.shop.handler.ShopHandler;
import com.solexgames.shop.menu.ShopCategoryMenu;
import com.solexgames.shop.purchasable.ShopPurchasableItem;
import com.solexgames.shop.purchasable.template.PrefixShopItem;
import com.solexgames.shop.purchasable.template.RankShopItem;
import com.solexgames.shop.settings.ShopSettings;
import me.lucko.helper.Commands;
import org.bukkit.plugin.java.JavaPlugin;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@Getter
public final class Shop extends JavaPlugin {

    @Getter
    private static Shop instance;

    private ShopSettings shopSettings;

    private CategoryHandler categoryHandler;
    private ItemHandler itemHandler;
    private ShopHandler shopHandler;
    private DataTypeHandler dataTypeHandler;

    private final ConfigFactory factory = ConfigFactory.newFactory(this);

    @Override
    public void onEnable() {
        instance = this;

        AdapterHandler.registerAdapter(ShopPurchasableItem.class, new ConfigurableShopItemAdapter());
        AdapterHandler.registerAdapter(ConfigurableShopCategory.class, new ConfigurableShopCategoryAdapter());

        this.shopSettings = this.factory.fromFile("settings", ShopSettings.class);

        this.categoryHandler = new CategoryHandler();
        this.itemHandler = new ItemHandler();
        this.shopHandler = new ShopHandler();
        this.dataTypeHandler = new DataTypeHandler();

        this.categoryHandler.loadFromConfig();
        this.itemHandler.loadFromConfig();
        this.dataTypeHandler.loadFromConfig();

        // temp
        this.categoryHandler.getCategories().clear();
        this.categoryHandler.getCategories().addAll(Arrays.asList(
                new RankShopCategory(),
                new PrefixShopCategory()
        ));

        this.itemHandler.getItems().clear();
        this.itemHandler.getItems().addAll(Arrays.asList(
                new RankShopItem(Rank.getByName("Basic"), 2000),
                new RankShopItem(Rank.getByName("Silver"), 5000),
                new RankShopItem(Rank.getByName("Gold"), 10000),
                new RankShopItem(Rank.getByName("Platinum"), 18000)
        ));

        this.itemHandler.getItems().addAll(Arrays.asList(
                new PrefixShopItem(Prefix.getByName("MLG"), 150),
                new PrefixShopItem(Prefix.getByName("YinYang"), 150),
                new PrefixShopItem(Prefix.getByName("Dropped"), 200),
                new PrefixShopItem(Prefix.getByName("Lightning"), 200),
                new PrefixShopItem(Prefix.getByName("Love"), 350),
                new PrefixShopItem(Prefix.getByName("egirl"), 500),
                new PrefixShopItem(Prefix.getByName("eboy"), 500)
        ));

        if (this.shopSettings.isShopEnabled()) {
            Commands.create().assertPlayer()
                    .handler(commandContext -> new ShopCategoryMenu().openMenu(commandContext.sender()))
                    .register("shop");
        }
    }

    @Override
    public void onDisable() {
        this.factory.save("settings", this.shopSettings);
    }
}
