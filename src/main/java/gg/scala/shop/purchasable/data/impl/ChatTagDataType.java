package gg.scala.shop.purchasable.data.impl;

import gg.scala.shop.purchasable.data.PurchasableDataType;
import gg.scala.shop.purchasable.data.exception.InvalidDataException;
import gg.scala.peach.PeachSpigotPlugin;
import gg.scala.peach.handler.ChatTagHandler;
import gg.scala.peach.model.ChatTag;
import gg.scala.peach.model.ChatTagPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author GrowlyX
 * @since 7/8/2021
 */

public class ChatTagDataType implements PurchasableDataType {

    @Override
    public String getName() {
        return "Chat Tags";
    }

    @Override
    public void applyToPlayer(Player player, List<String> data) {
        final ChatTag chatTag = ChatTagHandler.INSTANCE.findChatTag(data.get(0));
        final ChatTagPlayer chatTagPlayer = PeachSpigotPlugin.getInstance()
                .getPlayers().get(player.getUniqueId());

        if (chatTag == null) {
            throw new InvalidDataException("The prefix you tried to purchase does not exist anymore.");
        }

        if (chatTagPlayer.getOwnedTags().contains(chatTag.getUniqueId())) {
            throw new InvalidDataException("You already own the " + ChatColor.YELLOW + chatTag.getDisplayName() + ChatColor.RED + " chat tag!");
        }

        chatTagPlayer.getOwnedTags().add(chatTag.getUniqueId());
    }
}
