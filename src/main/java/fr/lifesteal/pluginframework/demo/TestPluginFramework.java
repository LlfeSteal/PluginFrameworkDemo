package fr.lifesteal.pluginframework.demo;

import fr.lifesteal.pluginframework.api.config.ConfigService;
import fr.lifesteal.pluginframework.core.command.PluginCommand;
import fr.lifesteal.pluginframework.core.plugin.PluginBase;
import fr.lifesteal.pluginframework.demo.business.DemoConfigService;
import fr.lifesteal.pluginframework.demo.business.DemoLangService;
import fr.lifesteal.pluginframework.demo.business.object.DemoData;
import fr.lifesteal.pluginframework.demo.command.DisplayDataCommand;
import fr.lifesteal.pluginframework.demo.command.PingCommand;
import fr.lifesteal.pluginframework.demo.command.WikiCommand;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class TestPluginFramework extends PluginBase {

    static {
        ConfigurationSerialization.registerClass(DemoData.class, "DemoData");
    }

    private DemoLangService demoLangService;
    private DemoConfigService demoConfigService;

    @Override
    public void init() {
        var langConfigRepository = getConfigRepositoryFactory().getNewYamlConfigFactory("demo", "lang.yml");
        demoLangService = new DemoLangService(getLogger(), langConfigRepository);

        var configRepository = getConfigRepositoryFactory().getNewYamlConfigFactory("demo", "config.yml");
        demoConfigService = new DemoConfigService(getLogger(), configRepository);
    }

    @Override
    protected List<PluginCommand> registerCommands() {
        return new ArrayList<>() {{
            add(getPluginCommandFactory()
                    .setName("ping")
                    .setDescription("Méthode de ping.")
                    .addAlias("png")
                    .setDefaultCommand(getCommandBaseBuilder()
                            .setPermission("demo.ping")
                            .setExecutorType(PingCommand.class)
                            .addExtraArgument(demoLangService)
                            .build())
                    .build());
            add(getPluginCommandFactory()
                    .setName("wiki")
                    .setDescription("Affichage du wiki.")
                    .addAlias("help")
                    .setDefaultCommand(getCommandBaseBuilder()
                            .setPermission("demo.wiki")
                            .setExecutorType(WikiCommand.class)
                            .addExtraArgument(demoLangService)
                            .build())
                    .build());
            add(getPluginCommandFactory()
                    .setName("displayData")
                    .setDescription("Affichage des données complexes.")
                    .addAlias("data")
                    .setDefaultCommand(getCommandBaseBuilder()
                            .setPermission("demo.displayData")
                            .setExecutorType(DisplayDataCommand.class)
                            .addExtraArgument(demoConfigService)
                            .build())
                    .build());
        }};
    }

    @Override
    protected List<ConfigService> registerConfigurationsServices() {
        return new ArrayList<>() {{
            add(demoLangService);
            add(demoConfigService);
        }};
    }

    @Override
    protected List<Listener> registerListeners() {
        return new ArrayList<>() {{
        }};
    }
}
