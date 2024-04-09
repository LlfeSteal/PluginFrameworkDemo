package fr.lifesteal.pluginframework.demo.command;

import fr.lifesteal.pluginframework.core.command.CommandExecutor;
import fr.lifesteal.pluginframework.demo.business.contract.DemoLanguageService;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class PingCommand extends CommandExecutor {

    private final DemoLanguageService demoLangService;

    public PingCommand(CommandSender issuer, Map<String, String> namedArgs, DemoLanguageService demoLangService) {
        super(issuer, namedArgs);
        this.demoLangService = demoLangService;
    }

    @Override
    public boolean prepare() {
        return true;
    }

    @Override
    public boolean execute() {
        getIssuer().sendMessage(demoLangService.getPongMessage());
        return true;
    }
}
