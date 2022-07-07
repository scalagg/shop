package gg.scala.shop.customizer;

import gg.scala.commons.acf.ConditionFailedException;
import gg.scala.commons.annotations.commands.customizer.CommandManagerCustomizer;
import gg.scala.commons.command.ScalaCommandManager;
import gg.scala.shop.Shop;
import gg.scala.shop.category.ShopCategory;
import net.md_5.bungee.api.ChatColor;

/**
 * @author GrowlyX
 * @since 7/5/2022
 */
public class ShopCommandCustomizer
{
    @CommandManagerCustomizer
    public void customize(
        ScalaCommandManager manager
    )
    {
        final Shop shop = Shop.getInstance();

        manager.getCommandCompletions()
            .registerAsyncCompletion("categories", k -> shop.getCategoryHandler().getCategoryNames());

        manager.getCommandContexts()
            .registerContext(ShopCategory.class, c -> {
                final String firstArg = String.join(" ", c.getArgs());
                final ShopCategory category = shop.getCategoryHandler().getById(firstArg).orElse(null);

                if (category == null) {
                    throw new ConditionFailedException(ChatColor.YELLOW + firstArg + ChatColor.RED + " is not a valid shop category.");
                }

                return category;
            });
    }
}
