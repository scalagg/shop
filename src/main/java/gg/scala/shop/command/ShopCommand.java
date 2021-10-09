package gg.scala.shop.command;

import gg.scala.shop.category.ShopCategory;
import gg.scala.shop.menu.ShopCategoryMenu;
import gg.scala.shop.menu.item.ShopCategorySubMenu;
import net.evilblock.cubed.acf.BaseCommand;
import net.evilblock.cubed.acf.annotation.CommandAlias;
import net.evilblock.cubed.acf.annotation.CommandCompletion;
import net.evilblock.cubed.acf.annotation.Default;
import net.evilblock.cubed.acf.annotation.Optional;
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
