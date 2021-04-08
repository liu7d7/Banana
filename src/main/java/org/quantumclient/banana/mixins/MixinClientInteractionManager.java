package org.quantumclient.banana.mixins;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.quantumclient.banana.event.EventBlockBreakingProgress;
import org.quantumclient.energy.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class MixinClientInteractionManager {

    @Inject(at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getEntityId()I", ordinal = 0)}, method = {"updateBlockBreakingProgress(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)Z"}, cancellable = true)
    private void onPlayerDamageBlock(BlockPos blockPos_1, Direction direction_1, CallbackInfoReturnable<Boolean> cir) {
        EventBlockBreakingProgress event = new EventBlockBreakingProgress(blockPos_1, direction_1);
        EventBus.post(event);
        if (event.isCancelled()) cir.cancel();
    }

}
