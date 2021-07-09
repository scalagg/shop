package com.solexgames.shop.purchasable.data.impl;

import com.solexgames.core.CorePlugin;
import com.solexgames.core.player.PotPlayer;
import com.solexgames.core.player.prefixes.Prefix;
import com.solexgames.core.player.ranks.Rank;
import com.solexgames.shop.purchasable.data.PurchasableDataType;
import com.solexgames.shop.purchasable.data.exception.InvalidDataException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class PrefixDataType implements PurchasableDataType {

    @Override
    public String getName() {
        return "Prefix";
    }

    @Override
    public void applyToPlayer(Player player, List<String> data) {
        final Prefix prefix = Prefix.getByName(data.get(0));
        final PotPlayer potPlayer = CorePlugin.getInstance().getPlayerManager().getPlayer(player);
        final String duration = data.get(1);

        if (prefix == null) {
            throw new InvalidDataException("The prefix you tried to purchase does not exist anymore.");
        }

        if (potPlayer.getAllPrefixes().contains(prefix.getName())) {
            throw new InvalidDataException("You already own the " + ChatColor.YELLOW + prefix.getName() + ChatColor.RED + " prefix!");
        }

        potPlayer.getAllPrefixes().add(prefix.getName());
    }
}
