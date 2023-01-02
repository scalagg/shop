package gg.scala.shop.purchasable.data.impl;

import gg.scala.chattags.plugin.handler.ChatTagsService;
import gg.scala.chattags.plugin.model.ChatTag;
import gg.scala.chattags.plugin.model.ChatTagPlayer;
import gg.scala.chattags.plugin.service.ChatTagsPlayerService;
import gg.scala.shop.purchasable.data.PurchasableDataType;
import gg.scala.shop.purchasable.data.exception.InvalidDataException;
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
        final ChatTag chatTag = ChatTagsService.INSTANCE.findChatTag(data.get(0));
        final ChatTagPlayer chatTagPlayer = ChatTagsPlayerService.INSTANCE.find(player);

        if (chatTag == null) {
            throw new InvalidDataException("The prefix you tried to purchase does not exist anymore.");
        }

        assert chatTagPlayer != null;

        if (chatTagPlayer.getOwnedTags().contains(chatTag.getUniqueId())) {
            throw new InvalidDataException("You already own the " + ChatColor.YELLOW + chatTag.getDisplayName() + ChatColor.RED + " chat tag!");
        }

        chatTagPlayer.getOwnedTags().add(chatTag.getUniqueId());
    }
}
