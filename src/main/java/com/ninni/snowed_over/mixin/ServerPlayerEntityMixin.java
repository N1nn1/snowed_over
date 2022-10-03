package com.ninni.snowed_over.mixin;

import com.ninni.snowed_over.client.screen.ReindeerScreenHandler;
import com.ninni.snowed_over.entity.ReindeerEntity;
import com.ninni.snowed_over.network.SnowedOverPacketIdentifiers;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerEntityMixin {


    @Shadow public abstract void closeContainer();

    @Shadow protected abstract void nextContainerCounter();

    @Shadow private int containerCounter;

    @Shadow protected abstract void initMenu(AbstractContainerMenu abstractContainerMenu);

    @Inject(at = @At("HEAD"), method = "openHorseInventory", cancellable = true)
    private void openHorseInventory(AbstractHorse horse, Container inventory, CallbackInfo ci) {
        ServerPlayer $this = (ServerPlayer) (Object) this;
        if (horse instanceof ReindeerEntity reindeer) {
            ci.cancel();
            if ($this.containerMenu != $this.inventoryMenu) {
                this.closeContainer();
            }
            this.nextContainerCounter();
            FriendlyByteBuf buf = PacketByteBufs.create();
            buf.writeInt(reindeer.getId());
            buf.writeInt(inventory.getContainerSize());
            buf.writeInt(this.containerCounter);
            ServerPlayNetworking.send($this, SnowedOverPacketIdentifiers.OPEN_REINDEER_SCREEN, buf);
            $this.containerMenu = new ReindeerScreenHandler(this.containerCounter, $this.getInventory(), inventory, reindeer);
            this.initMenu($this.containerMenu);
        }
    }

}
