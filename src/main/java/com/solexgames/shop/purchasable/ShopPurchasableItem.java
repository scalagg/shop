package com.solexgames.shop.purchasable;

import com.solexgames.core.CorePlugin;
import com.solexgames.core.player.PotPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@Getter
@RequiredArgsConstructor
public class ShopPurchasableItem {

    private final String name;
    private final String categoryName;
    private final String dataType;

    private final List<String> description;
    private final List<String> data;

    private final String material;
    private final int price;

    public boolean canPurchase(Player player) {
        final PotPlayer potPlayer = CorePlugin.getInstance().getPlayerManager().getPlayer(player);

        return potPlayer.getExperience() >= this.price;
    }

    public Material getMaterial() {
        return Material.getMaterial(this.material);
    }
}
