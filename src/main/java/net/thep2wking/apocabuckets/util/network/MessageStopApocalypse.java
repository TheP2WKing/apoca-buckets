package net.thep2wking.apocabuckets.util.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.thep2wking.apocabuckets.util.handler.ModWorldSavedData;

public class MessageStopApocalypse implements IMessage {
    private boolean stop;

    public MessageStopApocalypse() {
    }

    public MessageStopApocalypse(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        stop = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(stop);
    }

    public static class Handler implements IMessageHandler<MessageStopApocalypse, IMessage> {
        @Override
        public IMessage onMessage(MessageStopApocalypse message, MessageContext ctx) {
            WorldServer world = ctx.getServerHandler().player.getServerWorld();
            ModWorldSavedData apocalypseData = ModWorldSavedData.get(world);
            if (apocalypseData != null) {
                apocalypseData.toggleStopApocalypse();
            }
            return null;
        }
    }
}