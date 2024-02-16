package com.sanqiu.rustv2.util;

import org.bukkit.Color;
import org.bukkit.entity.Player;

public class MessageUtil {
    public static void sendMessage(Player player, String text, String color)
    {
        player.sendTitle("",color+text,10,70,20);
    }
    public static void sendPromptMessage(Player player, String text)
    {
        player.sendTitle("", Color.WHITE +text,10,70,20);
    }
    public static void sendWarningMessage(Player player, String text)
    {
        player.sendTitle("",Color.RED+text,10,70,20);
    }
    public static void sendRewardMessage(Player player, String text)
    {
        player.sendTitle("",Color.GREEN+text,10,70,20);
    }

}
