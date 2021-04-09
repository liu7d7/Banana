package org.quantumclient.banana.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.quantumclient.banana.event.EventQuadrupleTick;
import org.quantumclient.banana.event.EventSingleTick;
import org.quantumclient.banana.event.EventTwelvetupleTick;
import org.quantumclient.energy.EventBus;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ClientPlayerEntity.class, priority = 10000)
public class MixinClientPlayerEntity extends AbstractClientPlayerEntity {

    @Shadow
    @Final
    protected MinecraftClient client;
    public MixinClientPlayerEntity(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(at = @At("INVOKE"), method = "tick")
    public void tick9(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
        EventQuadrupleTick fortnite = new EventQuadrupleTick();
        EventBus.post(fortnite);
    }

    @Inject(at = @At("INVOKE"), method = "sendMovementPackets")
    public void tick10(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }

    @Inject(at = @At("HEAD"), method = "sendMovementPackets")
    public void tick11(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }

    @Inject(at = @At("RETURN"), method = "sendMovementPackets")
    public void tick12(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }

    @Inject(at = @At("TAIL"), method = "sendMovementPackets")
    public void tick13(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }

    @Inject(at = @At("RETURN"), method = "tick()V", cancellable = true)
    public void tick(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
        EventSingleTick event  = new EventSingleTick();
        EventBus.post(event);
        EventQuadrupleTick fortnite = new EventQuadrupleTick();
        EventBus.post(fortnite);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick2(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
        EventQuadrupleTick fortnite = new EventQuadrupleTick();
        EventBus.post(fortnite);
    }

    @Inject(at = @At("HEAD"), method = "tickMovement")
    public void tick4(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }

    @Inject(at = @At("INVOKE"), method = "tickMovement")
    public void tick5(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }

    @Inject(at = @At("RETURN"), method = "tickMovement")
    public void tick6(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }

    @Inject(at = @At("TAIL"), method = "tickMovement")
    public void tick7(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
    }

    @Inject(at = @At("INVOKE"), method = "tick")
    public void tick3(CallbackInfo info) {
        EventTwelvetupleTick EventTwelvetupleTick = new EventTwelvetupleTick();
        EventBus.post(EventTwelvetupleTick);
        EventQuadrupleTick fortnite = new EventQuadrupleTick();
        EventBus.post(fortnite);
    }

}
