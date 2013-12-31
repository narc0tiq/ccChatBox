package cc.chatbox;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraftforge.common.Configuration;

public class CommonProxy {
    public void init(Configuration config) {
        initConfig(config);
        initLanguage();
    }

    public void initConfig(Configuration config) {
        try { config.load(); }
        catch(RuntimeException e) { /* and ignore it. We'll just regen the config. */ }

        // TODO: Config stuff here.

        try { config.save(); }
        catch(RuntimeException e) {
            System.err.println("ccChatBox: can't save my config! This may be a problem.");
            e.printStackTrace(System.err);
        }
    }

    public void initLanguage() {
        String langDir = "/lang/";
        String[] languages = { "en_US" };
        LanguageRegistry lr = LanguageRegistry.instance();

        for(String lang: languages) {
            lr.loadLocalization(langDir + lang + ".xml", lang, true);
        }
    }
}
