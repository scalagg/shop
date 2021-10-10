package gg.scala.shop;

import gg.scala.lemon.Lemon;
import gg.scala.lemon.handler.RankHandler;
import gg.scala.shop.adapter.ConfigurableShopCategoryAdapter;
import gg.scala.shop.adapter.ConfigurableShopItemAdapter;
import gg.scala.shop.category.ShopCategory;
import gg.scala.shop.category.impl.ConfigurableShopCategory;
import gg.scala.shop.category.impl.global.PrefixShopCategory;
import gg.scala.shop.category.impl.global.RankShopCategory;
import gg.scala.shop.command.ShopCommand;
import gg.scala.shop.currency.CurrencyHandler;
import gg.scala.shop.currency.impl.GrapeCurrencyHandler;
import gg.scala.shop.handler.CategoryHandler;
import gg.scala.shop.handler.DataTypeHandler;
import gg.scala.shop.handler.ItemHandler;
import gg.scala.shop.handler.ShopHandler;
import gg.scala.shop.purchasable.ShopPurchasableItem;
import gg.scala.shop.purchasable.template.RankShopItem;
import gg.scala.shop.settings.ShopSettings;
import lombok.Getter;
import lombok.Setter;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import net.evilblock.cubed.acf.ConditionFailedException;
import net.evilblock.cubed.command.manager.CubedCommandManager;
import org.bukkit.ChatColor;
import xyz.mkotb.configapi.ConfigFactory;
import xyz.mkotb.configapi.internal.adapt.AdapterHandler;

import java.util.Arrays;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@Getter
public final class Shop extends ExtendedJavaPlugin {

    @Getter
    private static Shop instance;

    private ShopSettings shopSettings;

    private CategoryHandler categoryHandler;
    private ItemHandler itemHandler;
    private ShopHandler shopHandler;
    private DataTypeHandler dataTypeHandler;

    @Setter
    private CurrencyHandler currencyHandler;

    private final ConfigFactory factory = ConfigFactory.newFactory(this);

    @Override
    public void enable() {
        instance = this;

        AdapterHandler.registerAdapter(ShopPurchasableItem.class, new ConfigurableShopItemAdapter());
        AdapterHandler.registerAdapter(ConfigurableShopCategory.class, new ConfigurableShopCategoryAdapter());

        this.shopSettings = this.factory.fromFile("settings", ShopSettings.class);

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
                    new PrefixShopCategory()
            ));

            if (Lemon.getInstance().getLemonWebData().getServerName().equals("PvPBar")) {
                this.itemHandler.getItems().clear();
                this.itemHandler.getItems().addAll(Arrays.asList(
                        new RankShopItem(RankHandler.INSTANCE.findRank("Basic"), 2000),
                        new RankShopItem(RankHandler.INSTANCE.findRank("Silver"), 5000),
                        new RankShopItem(RankHandler.INSTANCE.findRank("Gold"), 10000),
                        new RankShopItem(RankHandler.INSTANCE.findRank("Platinum"), 18000)
                ));

//                this.itemHandler.getItems().addAll(Arrays.asList(
//                        new ChatTagShopItem(ChatTagHandler.INSTANCE.findChatTag("MLG"), 150),
//                        new ChatTagShopItem(ChatTagHandler.INSTANCE.findChatTag("YinYang"), 150),
//                        new ChatTagShopItem(ChatTagHandler.INSTANCE.findChatTag("Dropped"), 200),
//                        new ChatTagShopItem(ChatTagHandler.INSTANCE.findChatTag("Lightning"), 200),
//                        new ChatTagShopItem(ChatTagHandler.INSTANCE.findChatTag("Love"), 350),
//                        new ChatTagShopItem(ChatTagHandler.INSTANCE.findChatTag("egirl"), 500),
//                        new ChatTagShopItem(ChatTagHandler.INSTANCE.findChatTag("eboy"), 500)
//                ));
            }

            this.currencyHandler = new GrapeCurrencyHandler();

            final CubedCommandManager acfCommandProcessor = new CubedCommandManager(
                    this,
                    ChatColor.valueOf(Lemon.instance.lemonWebData.getPrimary()),
                    ChatColor.valueOf(Lemon.instance.lemonWebData.getSecondary())
            );

            acfCommandProcessor.getCommandCompletions()
                    .registerAsyncCompletion("categories", k -> this.categoryHandler.getCategoryNames());

            acfCommandProcessor.getCommandContexts()
                    .registerContext(ShopCategory.class, c -> {
                        final String firstArg = c.popFirstArg();
                        final ShopCategory category = this.categoryHandler.getById(firstArg).orElse(null);

                        if (category == null) {
                            throw new ConditionFailedException(ChatColor.YELLOW + firstArg + ChatColor.RED + " is not a valid shop category.");
                        }

                        return category;
                    });

            acfCommandProcessor.registerCommand(new ShopCommand());
        }
    }
}
