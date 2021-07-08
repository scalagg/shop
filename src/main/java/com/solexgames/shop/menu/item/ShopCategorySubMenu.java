package com.solexgames.shop.menu.item;

import com.solexgames.core.util.external.Button;
import com.solexgames.core.util.external.Menu;
import com.solexgames.shop.category.ShopCategory;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@RequiredArgsConstructor
public class ShopCategorySubMenu extends Menu {

    private final ShopCategory category;

    @Override
    public String getTitle(Player player) {
        return "Shop » " + this.category.getName();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttonMap = new HashMap<>();

        return buttonMap;
    }
}
