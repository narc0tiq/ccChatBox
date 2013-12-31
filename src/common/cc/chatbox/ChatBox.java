package cc.chatbox;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

import net.minecraftforge.common.Configuration;

@Mod(modid="ccChatBox",
     name="CC Chat Box Peripheral",
     version="%conf:VERSION%",
     dependencies="required-after:ComputerCraft;after:CCTurtle")
@NetworkMod(serverSideRequired=false,
            clientSideRequired=true,
            versionBounds="%conf:VERSION_BOUNDS%")
public class ChatBox {
    @SidedProxy(clientSide="cc.chatbox.ClientProxy",
                serverSide="cc.chatbox.CommonProxy")
    public static CommonProxy proxy;

    public static Configuration config = null;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(config);
    }
}
