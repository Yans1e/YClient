package de.yansie;

import de.yansie.client.gui.screen.WelcomeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class Client {
    public static Minecraft minecraft;
    public static void init(Minecraft minecraft){
        System.out.println("-------------\nINIT\n-------------");
        Client.minecraft = minecraft;
    }
    public static void linit(){ //later init when textures & screens are loaded
        System.out.println("-------------\nLINIT\n-------------");


        minecraft.setScreen(new WelcomeScreen(Component.literal("Hallo "+minecraft.getUser().getName())));

    }
    public static void tick(){
        //Color.HSBtoRGB((System.currentTimeMillis() % 7000) / 7000f, 1f, 1f)
        if (minecraft.player != null) {
            var c = Component.literalWithStyle("Hal",new Style(TextColor.fromRgb(Color.HSBtoRGB((System.currentTimeMillis() % 7000) / 7000f, 1f, 1f))));
            minecraft.player.displayClientMessage(c,true);
            //minecraft.player.displayClientMessage(Component.literal(String.valueOf(((Float) ((System.currentTimeMillis() % 7000) / 7000f)))), true);
        }
    }
    public static void reloadClasses(){
        new PauseScreen(true);
    }





}
