package fr.lifesteal.pluginframework.demo.command;

import fr.lifesteal.pluginframework.core.command.CommandExecutor;
import fr.lifesteal.pluginframework.demo.business.DemoConfigService;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class DisplayDataCommand extends CommandExecutor {
    private final DemoConfigService demoConfigService;

    public DisplayDataCommand(CommandSender issuer, Map<String, String> namedArgs, DemoConfigService demoConfigService) {
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
