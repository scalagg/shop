package com.solexgames.shop.handler;

import com.solexgames.core.util.BukkitUtil;
import com.solexgames.shop.ShopSpigotPlugin;
import com.solexgames.shop.purchasable.data.PurchasableDataType;
import com.solexgames.shop.settings.ShopSettings;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@Getter
public class DataTypeHandler {

    private final List<PurchasableDataType> dataTypes = new ArrayList<>();

    public void loadFromConfig() {
        final ShopSettings shopSettings = ShopSpigotPlugin.getInstance().getShopSettings();

        shopSettings.getDataTypes().forEach(dataTypePackage -> {
            final Collection<Class<?>> classCollection = BukkitUtil.getClassesInPackage(ShopSpigotPlugin.getInstance(), dataTypePackage);

            classCollection.forEach(this::attemptToRegisterClass);
        });
    }

    public PurchasableDataType findById(String id) {
        return this.dataTypes.stream()
                .filter(purchasableDataType -> purchasableDataType.getName().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }

    @SneakyThrows
    private void attemptToRegisterClass(Class<?> aClass) {
        final Object object = aClass.newInstance();

        if (object instanceof PurchasableDataType) {
            this.dataTypes.add((PurchasableDataType) object);
        }
    }
}
