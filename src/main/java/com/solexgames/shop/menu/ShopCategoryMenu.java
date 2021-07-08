package com.solexgames.shop.menu;

import com.solexgames.core.util.builder.ItemBuilder;
import com.solexgames.core.util.external.Button;
import com.solexgames.core.util.external.Menu;
import com.solexgames.shop.Shop;
import com.solexgames.shop.menu.item.ShopCategorySubMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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

    @Override
    public String getTitle(Player player) {
        return "Shop » Categories";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttonMap = new HashMap<>();
        final AtomicInteger atomicInteger = new AtomicInteger(10);

        Shop.getInstance().getCategoryHandler().getCategories().forEach(shopCategory -> {
            final List<String> finalDescription = new ArrayList<>(shopCategory.getDescription());

            finalDescription.add(" ");
            finalDescription.add(ChatColor.YELLOW + "[Click to view products]");

            buttonMap.put(atomicInteger.getAndIncrement(), new ItemBuilder(shopCategory.getMaterial())
                    .setDisplayName(ChatColor.GREEN + shopCategory.getName())
                    .addLore(finalDescription)
                    .toButton((player1, clickType) -> new ShopCategorySubMenu(shopCategory).openMenu(player))
            );
        });

        return buttonMap;
    }

    @Override
    public int getSize() {
        return 27;
    }
}
