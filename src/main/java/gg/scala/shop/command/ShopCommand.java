package gg.scala.shop.command;

import gg.scala.commons.acf.annotation.CommandAlias;
import gg.scala.commons.acf.annotation.CommandCompletion;
import gg.scala.commons.acf.annotation.Default;
import gg.scala.commons.acf.annotation.Optional;
import gg.scala.commons.annotations.commands.AutoRegister;
import gg.scala.commons.command.ScalaCommand;
import gg.scala.shop.category.ShopCategory;
import gg.scala.shop.menu.ShopCategoryMenu;
import gg.scala.shop.menu.item.ShopCategorySubMenu;
import org.bukkit.entity.Player;

/**
 * @author GrowlyX
 * @since 8/7/2021
 */
@AutoRegister
@CommandAlias("shop|purchase|buy|ranks")
public class ShopCommand extends ScalaCommand {

    @Default
    @CommandCompletion("@categories")
    public void onDefault(
        Player player,
        @Optional ShopCategory category
    ) {
        if (category == null) {
            new ShopCategoryMenu().openMenu(player);
        } else {
            new ShopCategorySubMenu(category).openMenu(player);
        }
    }
}
