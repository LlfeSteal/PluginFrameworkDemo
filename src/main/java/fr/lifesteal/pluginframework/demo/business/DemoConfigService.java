package fr.lifesteal.pluginframework.demo.business;

import fr.lifesteal.pluginframework.api.config.ConfigRepository;
import fr.lifesteal.pluginframework.core.config.ConfigParam;
import fr.lifesteal.pluginframework.core.config.ConfigServiceBase;
import fr.lifesteal.pluginframework.demo.business.contract.DemoConfigurationService;
import fr.lifesteal.pluginframework.demo.business.object.DemoData;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DemoConfigService extends ConfigServiceBase implements DemoConfigurationService {
    @ConfigParam(paramKey = "data")
    private List<DemoData> demoData = new ArrayList<>(){{
        add(new DemoData(1, "name1"));
        add(new DemoData(2, "name2"));
        add(new DemoData(3, "name3"));
    }};

    public DemoConfigService(Logger logger, ConfigRepository configRepository) {
        super(logger, configRepository);
    }

    @Override
    public List<DemoData> getDemoData() {
        return demoData;
    }
}
