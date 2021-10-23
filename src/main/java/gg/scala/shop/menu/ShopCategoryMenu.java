package gg.scala.shop.menu;

import gg.scala.shop.Shop;
import gg.scala.shop.menu.item.ShopCategorySubMenu;
import net.evilblock.cubed.menu.Button;
import net.evilblock.cubed.menu.Menu;
import net.evilblock.cubed.util.bukkit.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class ShopCategoryMenu extends Menu {

    {
        setPlaceholdBorders(true);
    }

    @Override
    public int size(@NotNull Map<Integer, ? extends Button> buttons) {
        return 27;
    }

    @NotNull
    @Override
    public String getTitle(@NotNull Player player) {
        final int currentAmount = Shop.getInstance().getCurrencyHandler().getAmount(player);

        return "Shop » Main (" + Shop.getInstance().getCurrencyHandler().getId() + ": " + currentAmount + ")";
    }

    @NotNull
    @Override
    public Map<Integer, Button> getButtons(@NotNull Player player) {
        final Map<Integer, Button> buttonMap = new HashMap<>();
        final AtomicInteger atomicInteger = new AtomicInteger(11);

        Shop.getInstance().getCategoryHandler().getCategories().stream().filter(shopCategory -> shopCategory != null && shopCategory.getDescription() != null).forEach(shopCategory -> {
            final List<String> finalDescription = new ArrayList<>(shopCategory.getDescription());

            finalDescription.add(" ");
            finalDescription.add(ChatColor.YELLOW + "Click to view products.");

            buttonMap.put(atomicInteger.getAndIncrement(), new ItemBuilder(shopCategory.getMaterial())
                    .name(ChatColor.GREEN + shopCategory.getName())
                    .setLore(finalDescription)
                    .toButton((player1, clickType) -> new ShopCategorySubMenu(shopCategory).openMenu(player))
            );
        });

        return buttonMap;
    }
}
