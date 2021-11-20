package gg.scala.shop.menu.item;

import gg.scala.shop.Shop;
import gg.scala.shop.category.ShopCategory;
import gg.scala.shop.menu.ShopCategoryMenu;
import gg.scala.shop.purchasable.ShopPurchasableItem;
import lombok.RequiredArgsConstructor;
import net.evilblock.cubed.menu.Button;
import net.evilblock.cubed.menu.Menu;
import net.evilblock.cubed.menu.pagination.PaginatedMenu;
import net.evilblock.cubed.util.bukkit.ItemBuilder;
import net.evilblock.cubed.util.bukkit.Tasks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@RequiredArgsConstructor
public class ShopCategorySubMenu extends PaginatedMenu {

    private static final List<Integer> SLOTS = Arrays.asList(
            10, 11, 12, 13, 14, 15, 16,
            19, 20, 21, 22, 23, 24, 25
    );

    private final ShopCategory category;

    {
        setPlaceholdBorders(true);
    }

    @Override
    public int size(@NotNull Map<Integer, ? extends Button> buttons) {
        return 36;
    }

    @Override
    public void onClose(@NotNull Player player, boolean manualClose) {
        if (manualClose) {
            Tasks.sync(() -> {
                new ShopCategoryMenu().openMenu(player);
                return null;
            });
        }
    }

    @NotNull
    @Override
    public List<Integer> getAllPagesButtonSlots() {
        return SLOTS;
    }

    @NotNull
    @Override
    public Map<Integer, Button> getAllPagesButtons(@NotNull Player player) {
        final Map<Integer, Button> buttonMap = new HashMap<>();
        final AtomicInteger atomicInteger = new AtomicInteger(10);
        final List<ShopPurchasableItem> itemList = Shop.getInstance().getItemHandler()
                .fetchItemsByCategory(this.category);

        itemList.forEach(shopPurchasableItem -> {
            final List<String> finalDescription = new ArrayList<>(shopPurchasableItem.getDescription());

            finalDescription.add(" ");
            finalDescription.add(ChatColor.GRAY + "Price: " + ChatColor.WHITE + shopPurchasableItem.getPrice() + " " + Shop.getInstance().getCurrencyHandler().getId());
            finalDescription.add(" ");
            finalDescription.add(shopPurchasableItem.canPurchase(player) ? ChatColor.YELLOW + "Click to purchase!" : ChatColor.RED + "You don't have enough money.");

            buttonMap.put(atomicInteger.getAndIncrement(), new ItemBuilder(shopPurchasableItem.getMaterial())
                    .name(ChatColor.GREEN + shopPurchasableItem.getName())
                    .setLore(finalDescription)
                    .toButton((player1, clickType) -> Shop.getInstance().getShopHandler().attemptToPurchase(player, shopPurchasableItem))
            );
        });

        return buttonMap;
    }

    @NotNull
    @Override
    public String getPrePaginatedTitle(@NotNull Player player) {
        final int currentAmount = Shop.getInstance().getCurrencyHandler().getAmount(player);

        return "Shop » " + this.category.getName() + " (" + Shop.getInstance().getCurrencyHandler().getId() + ": " + currentAmount + ")";
    }
}
