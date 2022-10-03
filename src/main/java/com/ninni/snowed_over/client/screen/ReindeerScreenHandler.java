package com.ninni.snowed_over.client.screen;

import com.ninni.snowed_over.entity.ReindeerEntity;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ReindeerScreenHandler extends AbstractContainerMenu {
    private final Container inventory;
    private final ReindeerEntity entity;

    public ReindeerScreenHandler(int syncId, Inventory playerInventory, Container inventory, final ReindeerEntity entity) {
        super(null, syncId);
        int l;
        int k;
        this.inventory = inventory;
        this.entity = entity;
        int i = 3;
        inventory.startOpen(playerInventory.player);
        int j = -18;
        this.addSlot(new Slot(inventory, 0, 8, 18){

            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Items.SADDLE) && !this.hasItem() && entity.isSaddleable();
            }

            @Override
            public boolean isActive() {
                return entity.isSaddleable();
            }
        });
        this.addSlot(new Slot(inventory, 1, 8, 36){

            @Override
            public boolean mayPlace(ItemStack stack) {
                return entity.isArmor(stack);
            }

            @Override
            public boolean isActive() {
                return entity.canWearArmor();
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
        for (k = 0; k < 3; ++k) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + k * 9 + 9, 8 + l * 18, 102 + k * 18 + -18));
            }
        }
        for (k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return !this.entity.hasInventoryChanged(this.inventory) && this.inventory.stillValid(player) && this.entity.isAlive() && this.entity.distanceTo(player) < 8.0f;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            itemStack = itemStack2.copy();
            int i = this.inventory.getContainerSize();
            if (index < i) {
                if (!this.moveItemStackTo(itemStack2, i, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).mayPlace(itemStack2) && !this.getSlot(1).hasItem()) {
                if (!this.moveItemStackTo(itemStack2, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).mayPlace(itemStack2)) {
                if (!this.moveItemStackTo(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (i <= 2 || !this.moveItemStackTo(itemStack2, 2, i, false)) {
                int k;
                int l = k = i + 27;
                int m = l + 9;
                if (index >= l && index < m ? !this.moveItemStackTo(itemStack2, i, k, false) : index < k ? !this.moveItemStackTo(itemStack2, l, m, false) : !this.moveItemStackTo(itemStack2, l, k, false)) {
                    return ItemStack.EMPTY;
                }
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemStack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.inventory.stopOpen(player);
    }
}
