package com.solexgames.shop.menu;

import com.solexgames.core.util.external.Button;
import com.solexgames.core.util.external.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

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

        return buttonMap;
    }
}
