package com.ninni.snowed_over.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.argument.RegistryPredicateArgumentType;
import net.minecraft.server.command.LocateCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocateCommand.class)
public class LocateCommandMixin {
    private static final SimpleCommandExceptionType IGLOO_NULL = new SimpleCommandExceptionType(new TranslatableText("snowed_over.commands.locate.revamped_igloo"));

    @Inject(at = @At("HEAD"), method = "execute", cancellable = true)
    private static void execute(ServerCommandSource source, RegistryPredicateArgumentType.RegistryPredicate<ConfiguredStructureFeature<?, ?>> structureFeature, CallbackInfoReturnable<Integer> cir)  throws CommandSyntaxException {
        if (structureFeature.test(ConfiguredStructureFeatures.IGLOO)) {
            throw IGLOO_NULL.create();
        }
    }


}
