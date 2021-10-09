package gg.scala.shop.purchasable;

import gg.scala.shop.Shop;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
        return Shop.getInstance().getCurrencyHandler().getAmount(player) >= this.price;
    }

    public Material getMaterial() {
        return Material.getMaterial(this.material);
    }
}
