package com.solexgames.shop;

import com.solexgames.lib.processor.config.ConfigFactory;
import com.solexgames.shop.settings.ShopSettings;
import org.bukkit.plugin.java.JavaPlugin;
import lombok.Getter;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

@Getter
public final class Shop extends JavaPlugin {

    @Getter
    private static Shop instance;

    private ShopSettings shopSettings;

    private final ConfigFactory factory = ConfigFactory.newFactory(this);

    @Override
    public void onEnable() {
        instance = this;

        this.shopSettings = this.factory.fromFile("settings", ShopSettings.class);
    }

    @Override
    public void onDisable() {
        this.factory.save("settings", this.shopSettings);
    }
}
