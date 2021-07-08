package com.solexgames.shop.purchasable.data.impl;

import com.solexgames.core.CorePlugin;
import com.solexgames.core.player.PotPlayer;
import com.solexgames.core.player.ranks.Rank;
import com.solexgames.shop.purchasable.data.PurchasableDataType;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class RankDataType implements PurchasableDataType {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Boolean applyToPlayer(Player player, List<String> data) {
        final Rank rank = Rank.getByName(data.get(0));
        final PotPlayer potPlayer = CorePlugin.getInstance().getPlayerManager().getPlayer(player);

        return null;
    }
}
