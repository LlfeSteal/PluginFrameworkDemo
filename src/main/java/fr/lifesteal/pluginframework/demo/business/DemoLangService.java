package fr.lifesteal.pluginframework.demo.business;

import fr.lifesteal.pluginframework.api.config.ConfigRepository;
import fr.lifesteal.pluginframework.core.config.Colorized;
import fr.lifesteal.pluginframework.core.config.ConfigParam;
import fr.lifesteal.pluginframework.core.config.ConfigServiceBase;
import fr.lifesteal.pluginframework.demo.business.contract.DemoLanguageService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DemoLangService extends ConfigServiceBase implements DemoLanguageService {

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

    @Colorized
    @ConfigParam(paramKey = "messages.error.unknow-demodata-id")
    private String unknowDemoDataIdErrorMessage = "&cUnknown demo id : &f%demodata_id%";

    @Colorized
    @ConfigParam(paramKey = "messages.info.display-demodata")
    private String displayDataItemMessage = "&aYou selected data : %demodata_name%";

    public DemoLangService(Logger logger, ConfigRepository configRepository) {
        super(logger, configRepository);
    }

    @Override
    public String getPongMessage() {
        return pongMessage;
    }

    @Override
    public List<String> getWikiMessages() {
        return wikiMessages;
    }

    @Override
    public String getUnknowDemoDataIdErrorMessage(String demoId) {
        return unknowDemoDataIdErrorMessage.replaceAll("%demodata_id%", demoId);
    }

    @Override
    public String getDisplayDataItemMessage(String demoName) {
        return displayDataItemMessage.replaceAll("%demodata_name%", demoName);
    }
}
