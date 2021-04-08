package org.quantumclient.banana.mixins;

import net.minecraft.client.Keyboard;
import org.quantumclient.banana.event.KeyPressEvent;
import org.quantumclient.energy.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard {

    @Inject(at = @At("HEAD"), method = "onKey(JIIII)V", cancellable = true)
    private void onOnKey(long windowHandle, int keyCode, int scanCode, int action, int modifiers, CallbackInfo ci) {
        KeyPressEvent event = new KeyPressEvent(keyCode, action);
        EventBus.post(event);
        if (event.isCancelled()) ci.cancel();
    }

}
