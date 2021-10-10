package gg.scala.shop.currency.impl;

import gg.scala.grape.GrapeSpigotPlugin;
import gg.scala.grape.player.impl.PersistentGrapePlayer;
import gg.scala.shop.currency.CurrencyHandler;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author GrowlyX
 * @since 8/21/2021
 */

public class GrapeCurrencyHandler implements CurrencyHandler {

    @Override
    public String getId() {
        return "Coins";
    }

    @Override
    public int getAmount(Player player) {
        final PersistentGrapePlayer grapePlayer = this.getGrapePlayer(player).orElse(null);

        return grapePlayer == null ? 0 : grapePlayer.getCoins();
    }

    @Override
    public void remove(Player player, int amount) {
        this.getGrapePlayer(player).ifPresent(potPlayer -> {
            potPlayer.setCoins(potPlayer.getCoins() - amount);
        });
    }

    @Override
    public void add(Player player, int amount) {
        this.getGrapePlayer(player).ifPresent(potPlayer -> {
            potPlayer.setCoins(potPlayer.getCoins() + amount);
        });
    }

    public Optional<PersistentGrapePlayer> getGrapePlayer(Player player) {
        return Optional.of(GrapeSpigotPlugin.getInstance().getPlayerHandler().getByPlayer(player));
    }
}
