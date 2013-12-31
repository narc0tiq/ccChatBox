package cc.chatbox;

import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraftforge.common.Configuration;

public class CommonProxy {
    public int chatBoxBlockID = 1898;
    public BlockChatBox chatBoxBlock;
    public static final String NAME_CHAT_BOX = "cc.chatbox";

    public void init(Configuration config) {
        initConfig(config);
        initBlocks();
        initLocales();
    }

    public void initConfig(Configuration config) {
        try { config.load(); }
        catch(RuntimeException e) { /* and ignore it. We'll just regen the config. */ }

        chatBoxBlockID = config.getBlock("chatBox", chatBoxBlockID).getInt(chatBoxBlockID);

        try { config.save(); }
        catch(RuntimeException e) {
            System.err.println("ccChatBox: can't save my config! This may be a problem.");
            e.printStackTrace(System.err);
        }
    }

    public void initBlocks() {
        chatBoxBlock = new BlockChatBox(chatBoxBlockID);
        GameRegistry.registerBlock(chatBoxBlock, ItemBlock.class, NAME_CHAT_BOX);
        GameRegistry.registerTileEntity(TileEntityChatBox.class, NAME_CHAT_BOX);
    }

    public void initLocales() {
        String langDir = "/lang/";
        String[] languages = { "en_US" };
        LanguageRegistry lr = LanguageRegistry.instance();

        for(String lang: languages) {
            lr.loadLocalization(langDir + lang + ".xml", lang, true);
        }
    }

    @SuppressWarnings("unchecked")
    public void chatToAll(ChatMessageComponent message) {
        List<EntityPlayerMP> players = (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList;

        for(EntityPlayerMP p: players) {
            p.sendChatToPlayer(message);
        }
    }

    public void chatToPlayer(String username, ChatMessageComponent message) {
        EntityPlayerMP player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
        if(player == null) {
            return;
        }

        player.sendChatToPlayer(message);
    }
}
