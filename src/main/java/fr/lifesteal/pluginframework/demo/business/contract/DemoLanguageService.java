package fr.lifesteal.pluginframework.demo.business.contract;

import fr.lifesteal.pluginframework.api.config.ConfigService;

import java.util.List;

public interface DemoLanguageService extends ConfigService {
    String getPongMessage();
    List<String> getWikiMessages();
    String getUnknowDemoDataIdErrorMessage(String demoId);
    String getDisplayDataItemMessage(String demoName);
}
