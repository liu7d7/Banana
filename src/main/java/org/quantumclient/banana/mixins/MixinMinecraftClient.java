package org.quantumclient.banana.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.quantumclient.banana.event.EventOpenedScreen;
import org.quantumclient.banana.event.EventTwelvetupleTick;
import org.quantumclient.energy.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Inject(at = @At("HEAD"), method = "openScreen", cancellable = true)
    public void openScreen(Screen screen, CallbackInfo info) {
        EventOpenedScreen event = new EventOpenedScreen(screen);
        EventBus.post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(method = "updateWindowTitle()V", at = @At("HEAD"), cancellable = true)
    private void updateTitle(final CallbackInfo info){
        info.cancel();
    }

    @Inject(at = @At("INVOKE"), method = "tick")
    public void tick3(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick1(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }

    @Inject(at = @At("RETURN"), method = "tick")
    public void tick2(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }

    @Inject(at = @At("TAIL"), method = "tick")
    public void tick4(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }


}
