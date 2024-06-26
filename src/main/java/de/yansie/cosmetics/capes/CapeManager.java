package de.yansie.cosmetics.capes;

import de.yansie.Client;
import de.yansie.cosmetics.CosmeticManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

public class CapeManager implements CosmeticManager<Cape> {
    private Cape cape;
    private Minecraft minecraft;
    @Override
    public void setActive(Cape cape) {
        this.cape = cape;
    }

    @Override
    public Cape getCurrent() {
        return cape;
    }

    @Override
    public void tick() {
        if (minecraft == null) minecraft = Minecraft.getInstance();
    }

    @Override
    public void render() {
        if (cape==null) return;
        AbstractClientPlayer player = minecraft.player;
        if (player == null) return;
        ClientPacketListener connection = minecraft.getConnection();
        if (connection==null) return;
        PlayerInfo info = connection.getPlayerInfo(player.getUUID());
        if (info == null) return;
        ResourceLocation location = cape.getResourceLocation();

    }
}
