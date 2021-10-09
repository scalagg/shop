package gg.scala.shop.purchasable.data.impl;

import gg.scala.shop.purchasable.data.PurchasableDataType;
import gg.scala.shop.purchasable.data.exception.InvalidDataException;
import gg.scala.lemon.handler.PlayerHandler;
import gg.scala.lemon.handler.RankHandler;
import gg.scala.lemon.player.LemonPlayer;
import gg.scala.lemon.player.rank.Rank;
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
        final Rank rank = RankHandler.INSTANCE.findRank(data.get(0));
        final LemonPlayer lemonPlayer = PlayerHandler.INSTANCE.findPlayer(player).orElse(null);

        final String duration = data.get(1);

        if (rank == null) {
            throw new InvalidDataException("The rank you tried to purchase does not exist anymore.");
        }

        if (lemonPlayer == null || lemonPlayer.getActiveGrant().getRank().getWeight() > rank.getWeight()) {
            throw new InvalidDataException("Your current rank's weight is higher than the rank you are trying to purchase.");
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "grantmanual " + player.getName() + " " + rank.getName() + " " + duration + " Purchased via in-game shop");
    }
}
