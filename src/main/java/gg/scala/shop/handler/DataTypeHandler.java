package gg.scala.shop.handler;

import gg.scala.shop.Shop;
import gg.scala.shop.purchasable.data.PurchasableDataType;
import gg.scala.shop.purchasable.data.impl.ChatTagDataType;
import gg.scala.shop.purchasable.data.impl.RankDataType;
import gg.scala.shop.settings.ShopSettings;
import lombok.Getter;
import lombok.SneakyThrows;
import net.evilblock.cubed.util.ClassUtils;

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
        final ShopSettings shopSettings = Shop.getInstance().getShopSettings();

        shopSettings.getDataTypes().forEach(dataTypePackage -> {
            final Collection<Class<?>> classCollection = ClassUtils.getClassesInPackage(Shop.getInstance(), dataTypePackage);

            classCollection.forEach(this::attemptToRegisterClass);
        });

        dataTypes.add(new ChatTagDataType());
        dataTypes.add(new RankDataType());
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
