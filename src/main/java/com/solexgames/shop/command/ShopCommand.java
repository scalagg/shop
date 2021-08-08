package com.solexgames.shop.command;

import com.solexgames.lib.acf.BaseCommand;
import com.solexgames.lib.acf.annotation.CommandAlias;
import com.solexgames.lib.acf.annotation.CommandCompletion;
import com.solexgames.lib.acf.annotation.Default;
import com.solexgames.lib.acf.annotation.Optional;
import com.solexgames.shop.category.ShopCategory;
import com.solexgames.shop.menu.ShopCategoryMenu;
import com.solexgames.shop.menu.item.ShopCategorySubMenu;
import org.bukkit.entity.Player;

/**
 * @author GrowlyX
 * @since 8/7/2021
 */

@CommandAlias("shop|purchase|buy|ranks")
public class ShopCommand extends BaseCommand {

    @Default
    @CommandCompletion("@categories")
    public void onDefault(Player player, @Optional ShopCategory category) {
        if (category == null) {
            new ShopCategoryMenu().openMenu(player);
        } else {
            new ShopCategorySubMenu(category).openMenu(player);
        }
    }
}
