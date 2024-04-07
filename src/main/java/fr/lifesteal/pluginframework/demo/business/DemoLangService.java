package fr.lifesteal.pluginframework.demo.business;

import fr.lifesteal.pluginframework.api.config.ConfigRepository;
import fr.lifesteal.pluginframework.core.config.Colorized;
import fr.lifesteal.pluginframework.core.config.ConfigParam;
import fr.lifesteal.pluginframework.core.config.ConfigServiceBase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DemoLangService extends ConfigServiceBase {

    @Colorized
    @ConfigParam(paramKey = "messages.info.pong")
    private String pongMessage = "&aPong !.";

    @Colorized
    @ConfigParam(paramKey = "messages.info.wiki-messages")
    private List<String> wikiMessages = new ArrayList<>() {{
        add("&eVoici les liens de notre wiki : ");
        add("&aWiki 1 vert");
        add("&3Wiki 2 bleu");
        add("&4Wiki 3 rouge");
        add("&6Wiki 4 orange");
    }};

    public DemoLangService(Logger logger, ConfigRepository configRepository) {
        super(logger, configRepository);
    }

    public String getPongMessage() {
        return pongMessage;
    }

    public List<String> getWikiMessages() {
        return wikiMessages;
    }
}
