package gg.scala.shop.purchasable.data;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public interface PurchasableDataType {

    String getName();

    void applyToPlayer(Player player, List<String> data);

}
