package fr.lifesteal.pluginframework.demo.command;

import fr.lifesteal.pluginframework.core.command.CommandExecutor;
import fr.lifesteal.pluginframework.demo.business.contract.DemoConfigurationService;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class DisplayDataCommand extends CommandExecutor {
    private final DemoConfigurationService demoConfigService;

    public DisplayDataCommand(CommandSender issuer, Map<String, String> namedArgs, DemoConfigurationService demoConfigService) {
        super(issuer, namedArgs);
        this.demoConfigService = demoConfigService;
    }

    @Override
    public boolean prepare() {
        return true;
    }

    @Override
    public boolean execute() {
        var dataToDisplay = demoConfigService
                .getDemoData()
                .stream()
                .map(data -> data.getId() + " - " + data.getName())
                .toArray(String[]::new);

        getIssuer().sendMessage(dataToDisplay);

        return true;
    }
}
