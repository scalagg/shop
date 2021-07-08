package com.solexgames.shop.purchasable.data.impl;

import com.solexgames.core.CorePlugin;
import com.solexgames.core.player.PotPlayer;
import com.solexgames.core.player.ranks.Rank;
import com.solexgames.shop.purchasable.data.PurchasableDataType;
import com.solexgames.shop.purchasable.data.exception.InvalidDataException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class RankDataType implements PurchasableDataType {

    @Override
    public String getName() {
        return "Rank";
    }

    @Override
    public void applyToPlayer(Player player, List<String> data) {
        final Rank rank = Rank.getByName(data.get(0));
        final PotPlayer potPlayer = CorePlugin.getInstance().getPlayerManager().getPlayer(player);
        final String duration = data.get(1);

        if (rank == null) {
            throw new InvalidDataException("The rank you tried to purchase does not exist anymore.");
        }

        if (potPlayer.getActiveGrant().getRank().getWeight() > rank.getWeight()) {
            throw new InvalidDataException("Your current rank's weight is higher than the rank you are trying to purchase.");
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "grantmanual " + player.getName() + " " + rank.getName() + " " + duration + " Purchased via in-game shop");
    }
}
