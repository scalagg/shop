package com.solexgames.shop.currency;

import org.bukkit.entity.Player;

/**
 * @author GrowlyX
 * @since 8/21/2021
 */

public interface CurrencyHandler {

    String getId();

    int getAmount(Player player);

    void remove(Player player, int amount);

    void add(Player player, int amount);

}
