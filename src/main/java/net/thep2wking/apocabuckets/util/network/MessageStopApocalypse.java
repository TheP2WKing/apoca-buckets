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
            WorldServer worldServer = ctx.getServerHandler().player.getServerWorld();
            worldServer.addScheduledTask(() -> {
                ModWorldSavedData apocalypseData = ModWorldSavedData.get(worldServer);
                if (apocalypseData != null) {
                    apocalypseData.toggleStopApocalypse();
                }
            });
            return null;
        }
    }
}