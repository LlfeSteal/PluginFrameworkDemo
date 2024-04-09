package fr.lifesteal.pluginframework.demo.business.contract;

import fr.lifesteal.pluginframework.api.config.ConfigService;
import fr.lifesteal.pluginframework.demo.business.object.DemoData;

import java.util.List;

public interface DemoConfigurationService extends ConfigService {
    List<DemoData> getDemoData();
}
