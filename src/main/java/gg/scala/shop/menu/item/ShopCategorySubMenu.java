package gg.scala.shop.menu.item;

import gg.scala.shop.Shop;
import gg.scala.shop.category.ShopCategory;
import gg.scala.shop.menu.ShopCategoryMenu;
import gg.scala.shop.purchasable.ShopPurchasableItem;
import lombok.RequiredArgsConstructor;
import net.evilblock.cubed.menu.Button;
import net.evilblock.cubed.menu.Menu;
import net.evilblock.cubed.util.bukkit.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@RequiredArgsConstructor
public class ShopCategorySubMenu extends Menu {

    private final ShopCategory category;

    {
        setPlaceholder(true);
    }

    @NotNull
    @Override
    public String getTitle(@NotNull Player player) {
        final int currentAmount = Shop.getInstance().getCurrencyHandler().getAmount(player);

        return "Shop » " + this.category.getName() + " (" + Shop.getInstance().getCurrencyHandler().getId() + ": " + currentAmount + ")";
    }

    @NotNull
    @Override
    public Map<Integer, Button> getButtons(@NotNull Player player) {
        final Map<Integer, Button> buttonMap = new HashMap<>();
        final AtomicInteger atomicInteger = new AtomicInteger(10);
        final List<ShopPurchasableItem> itemList = Shop.getInstance().getItemHandler()
                .fetchItemsByCategory(this.category);

        itemList.forEach(shopPurchasableItem -> {
            final List<String> finalDescription = new ArrayList<>(shopPurchasableItem.getDescription());

            finalDescription.add(" ");
            finalDescription.add(ChatColor.GRAY + "Price: " + ChatColor.WHITE + shopPurchasableItem.getPrice() + " " + Shop.getInstance().getCurrencyHandler().getId());
            finalDescription.add(" ");
            finalDescription.add(shopPurchasableItem.canPurchase(player) ? ChatColor.YELLOW + "[Click to purchase item]" : ChatColor.RED + "[You don't have enough XP]");

            buttonMap.put(atomicInteger.getAndIncrement(), new ItemBuilder(shopPurchasableItem.getMaterial())
                    .name(ChatColor.GREEN + shopPurchasableItem.getName())
                    .setLore(finalDescription)
                    .toButton((player1, clickType) -> Shop.getInstance().getShopHandler().attemptToPurchase(player, shopPurchasableItem))
            );
        });

        buttonMap.put(16, new ItemBuilder(Material.BED)
                .name(ChatColor.RED + "Return to Main")
                .addToLore(
                        "&7Return back to the",
                        "&7main shop menu.",
                        " ",
                        "&eClick to open main."
                )
                .toButton((player1, clickType) -> {
                    new ShopCategoryMenu().openMenu(player1);
                })
        );

        return buttonMap;
    }

    @Override
    public int size(@NotNull Map<Integer, ? extends Button> buttons) {
        return 27;
    }
}
