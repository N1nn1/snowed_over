package com.ninni.snowed_over.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.ResourceOrTagLocationArgument;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.commands.LocateCommand;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocateCommand.class)
public class LocateCommandMixin {
    private static final SimpleCommandExceptionType IGLOO_NULL = new SimpleCommandExceptionType(new TranslatableComponent("snowed_over.commands.locate.revamped_igloo"));

    @Inject(at = @At("HEAD"), method = "locate", cancellable = true)
    private static void execute(CommandSourceStack source, ResourceOrTagLocationArgument.Result<ConfiguredStructureFeature<?, ?>> structureFeature, CallbackInfoReturnable<Integer> cir)  throws CommandSyntaxException {
        if (structureFeature.test(StructureFeatures.IGLOO)) {
            throw IGLOO_NULL.create();
        }
    }


}
