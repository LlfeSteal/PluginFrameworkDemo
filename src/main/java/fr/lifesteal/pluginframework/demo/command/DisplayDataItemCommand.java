package fr.lifesteal.pluginframework.demo.command;

import com.google.common.collect.ImmutableList;
import fr.lifesteal.pluginframework.core.command.CommandExecutor;
import fr.lifesteal.pluginframework.core.utils.StringUtils;
import fr.lifesteal.pluginframework.demo.business.contract.DemoConfigurationService;
import fr.lifesteal.pluginframework.demo.business.contract.DemoLanguageService;
import fr.lifesteal.pluginframework.demo.business.object.DemoData;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DisplayDataItemCommand extends CommandExecutor {

    private final DemoConfigurationService demoConfigService;
    private final DemoLanguageService demoLangService;
    private DemoData selectedData;

    public DisplayDataItemCommand(CommandSender issuer, Map<String, String> namedArgs, DemoConfigurationService demoConfigService, DemoLanguageService demoLangService) {
        super(issuer, namedArgs);
        this.demoConfigService = demoConfigService;
        this.demoLangService = demoLangService;
    }

    @Override
    public boolean prepare() {
        var inputDataId = getArg("item");
        var dataId = StringUtils.tryParseInteger(inputDataId);
        if (dataId == null) {
            getIssuer().sendMessage(this.demoLangService.getUnknowDemoDataIdErrorMessage(inputDataId));
            return false;
        }

        var inputData = this.demoConfigService.getDemoData().stream().filter(data -> data.getId() == dataId).findFirst();
        if (inputData.isEmpty()) {
            getIssuer().sendMessage(this.demoLangService.getUnknowDemoDataIdErrorMessage(inputDataId));
            return false;
        }

        this.selectedData = inputData.get();

        return true;
    }

    @Override
    public boolean execute() {
        getIssuer().sendMessage(this.demoLangService.getDisplayDataItemMessage(this.selectedData.getName()));
        return true;
    }

    @Override
    public List<String> getTabSuggestion(String paramName) {
        if (paramName.equals("item")) {
            return this.demoConfigService.getDemoData().stream().map(data -> String.valueOf(data.getId())).collect(Collectors.toList());
        }

        return ImmutableList.of();
    }
}
