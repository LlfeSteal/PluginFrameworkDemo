package unit.command;

import fr.lifesteal.pluginframework.demo.business.contract.DemoConfigurationService;
import fr.lifesteal.pluginframework.demo.business.contract.DemoLanguageService;
import fr.lifesteal.pluginframework.demo.business.object.DemoData;
import fr.lifesteal.pluginframework.demo.command.DisplayDataItemCommand;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DisplayDataItemCommandTest {
    @Test
    public void prepareShouldReturnTrue() {
        // Arrange
        var data = new ArrayList<DemoData>() {{
            add(new DemoData(1, "Name1"));
            add(new DemoData(2, "Name2"));
            add(new DemoData(3, "Name3"));
        }};

        var commandSender = mock(CommandSender.class);
        var args = new HashMap<String, String>(){{
            put("item", "2");
        }};

        var demoConfigService = mock(DemoConfigurationService.class);
        when(demoConfigService.getDemoData()).thenReturn(data);

        var demoLangService = mock(DemoLanguageService.class);

        var command = new DisplayDataItemCommand(commandSender, args, demoConfigService, demoLangService);

        // Act
        boolean result = command.prepare();

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"item, string", "anotherKey, anotherValue"})
    public void prepareShouldReturnFalse(String argKey, String argValue) {
        // Arrange
        var commandSender = mock(CommandSender.class);
        var args = new HashMap<String, String>(){{
           put(argKey, argValue);
        }};
        var demoConfigService = mock(DemoConfigurationService.class);
        var demoLangService = mock(DemoLanguageService.class);

        var command = new DisplayDataItemCommand(commandSender, args, demoConfigService, demoLangService);

        // Act
        boolean result = command.prepare();

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    public void DisplayDataItemCommandExecute() {
        // Arrange
        var data = new ArrayList<DemoData>() {{
            add(new DemoData(1, "Name1"));
            add(new DemoData(2, "Name2"));
            add(new DemoData(3, "Name3"));
        }};
        var expectedMessage = "Assertion message";

        var commandSender = mock(CommandSender.class);
        var args = new HashMap<String, String>(){{
            put("item", "2");
        }};

        var demoConfigService = mock(DemoConfigurationService.class);
        when(demoConfigService.getDemoData()).thenReturn(data);

        var demoLangService = mock(DemoLanguageService.class);
        when(demoLangService.getDisplayDataItemMessage("Name2")).thenReturn(expectedMessage);

        var command = new DisplayDataItemCommand(commandSender, args, demoConfigService, demoLangService);
        command.prepare();

        // Act
        command.execute();

        // Assert
        verify(commandSender, times(1)).sendMessage(expectedMessage);
    }

    @Test
    public void DisplayDataItemCommandGetTabSuggestionShouldReturnAllAvailableSuggestions() {
        // Arrange
        var data = new ArrayList<DemoData>() {{
            add(new DemoData(1, "Name1"));
            add(new DemoData(2, "Name2"));
            add(new DemoData(3, "Name3"));
        }};
        var commandSender = mock(CommandSender.class);
        var args = new HashMap<String, String>();

        var demoConfigService = mock(DemoConfigurationService.class);
        when(demoConfigService.getDemoData()).thenReturn(data);

        var demoLangService = mock(DemoLanguageService.class);

        var command = new DisplayDataItemCommand(commandSender, args, demoConfigService, demoLangService);

        // Act
        List<String> result = command.getTabSuggestion("item");

        // Assert
        var expectedResult = List.of("1", "2", "3");
        assertThat(result).containsExactlyElementsOf(expectedResult);
    }

    @Test
    public void DisplayDataItemCommandGetTabSuggestionShouldReturnEmptyList() {
        // Arrange
        var commandSender = mock(CommandSender.class);
        var args = new HashMap<String, String>();
        var demoConfigService = mock(DemoConfigurationService.class);
        var demoLangService = mock(DemoLanguageService.class);

        var command = new DisplayDataItemCommand(commandSender, args, demoConfigService, demoLangService);

        // Act
        List<String> result = command.getTabSuggestion("anotherString");

        // Assert
        assertThat(result).isEmpty();
    }
}
