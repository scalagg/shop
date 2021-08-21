package com.solexgames.shop.currency.impl;

import com.solexgames.core.CorePlugin;
import com.solexgames.core.player.PotPlayer;
import com.solexgames.shop.currency.CurrencyHandler;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author GrowlyX
 * @since 8/21/2021
 */

public class ExperienceCurrencyHandler implements CurrencyHandler {

    @Override
    public String getId() {
        return "XP";
    }

    @Override
    public int getAmount(Player player) {
        final PotPlayer potPlayer = this.getPotPlayer(player).orElse(null);

        return potPlayer == null ? 0 : potPlayer.getExperience();
    }

    @Override
    public void remove(Player player, int amount) {
        this.getPotPlayer(player).ifPresent(potPlayer -> {
            potPlayer.setExperience(potPlayer.getExperience() - amount);
        });
    }

    @Override
    public void add(Player player, int amount) {
        this.getPotPlayer(player).ifPresent(potPlayer -> {
            potPlayer.setExperience(potPlayer.getExperience() + amount);
        });
    }

    public Optional<PotPlayer> getPotPlayer(Player player) {
        return Optional.of(CorePlugin.getInstance().getPlayerManager().getPlayer(player));
    }
}
