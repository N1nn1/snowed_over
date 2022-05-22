package com.ninni.snowed_over.network;

import com.ninni.snowed_over.client.screen.ReindeerInventoryMenu;
import com.ninni.snowed_over.client.screen.ReindeerInventoryScreen;
import com.ninni.snowed_over.entity.ReindeerEntity;
import com.ninni.snowed_over.mixin.PlayerAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenReindeerScreenPacket {
    private final int containerId;
    private final int size;
    private final int entityId;

    public OpenReindeerScreenPacket(int containerIdIn, int sizeIdIn, int entityIdIn) {
        this.containerId = containerIdIn;
        this.size = sizeIdIn;
        this.entityId = entityIdIn;
    }

    public static OpenReindeerScreenPacket read(FriendlyByteBuf buf) {
        int containerId = buf.readUnsignedByte();
        int size = buf.readVarInt();
        int entityId = buf.readInt();
        return new OpenReindeerScreenPacket(containerId, size, entityId);
    }

    public static void write(OpenReindeerScreenPacket packet, FriendlyByteBuf buf) {
        buf.writeByte(packet.containerId);
        buf.writeVarInt(packet.size);
        buf.writeInt(packet.entityId);
    }

    public static void handle(OpenReindeerScreenPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer clientPlayer = minecraft.player;
            Entity entity = null;
            if (clientPlayer != null) {
                entity = clientPlayer.level.getEntity(packet.getEntityId());
            }
            if (entity instanceof ReindeerEntity reindeer) {
                SimpleContainer inventory = new SimpleContainer(packet.getSize());
                Inventory playerInventory = ((PlayerAccessor)clientPlayer).getInventory();
                ReindeerInventoryMenu reindeerContainer = new ReindeerInventoryMenu(packet.getContainerId(), playerInventory, inventory, reindeer);
                clientPlayer.containerMenu = reindeerContainer;
                ReindeerInventoryScreen reindeerScreen = new ReindeerInventoryScreen(reindeerContainer, playerInventory, reindeer);
                minecraft.setScreen(reindeerScreen);
            }
        });
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    public int getContainerId() {
        return this.containerId;
    }

    @OnlyIn(Dist.CLIENT)
    public int getSize() {
        return this.size;
    }

    @OnlyIn(Dist.CLIENT)
    public int getEntityId() {
        return this.entityId;
    }
}