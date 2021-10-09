package gg.scala.shop.handler;

import gg.scala.shop.Shop;
import gg.scala.shop.category.ShopCategory;
import gg.scala.shop.settings.ShopSettings;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@Getter
public class CategoryHandler {

    private final List<ShopCategory> categories = new ArrayList<>();

    public void loadFromConfig() {
        final ShopSettings shopSettings = Shop.getInstance().getShopSettings();

        this.categories.addAll(shopSettings.getShopCategories());
    }

    public Optional<ShopCategory> getById(String id) {
        return this.categories.stream()
                .filter(shopCategory -> shopCategory.getName().equalsIgnoreCase(id))
                .findFirst();
    }

    public List<String> getCategoryNames() {
        return this.categories.stream()
                .map(ShopCategory::getName)
                .collect(Collectors.toList());
    }
}
