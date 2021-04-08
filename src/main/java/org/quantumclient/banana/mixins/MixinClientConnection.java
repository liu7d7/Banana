package org.quantumclient.banana.mixins;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import org.quantumclient.banana.event.EventPacket;
import org.quantumclient.energy.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class MixinClientConnection {


    @Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> callback, CallbackInfo callbackInfo) {

        EventPacket event = new EventPacket.Send(packet);
        EventBus.post(event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void onChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
        EventPacket event = new EventPacket.Receive(packet);
        EventBus.post(event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }


}