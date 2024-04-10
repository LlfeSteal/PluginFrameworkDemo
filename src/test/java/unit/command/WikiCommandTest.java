package unit.command;

import fr.lifesteal.pluginframework.demo.business.contract.DemoLanguageService;
import fr.lifesteal.pluginframework.demo.command.WikiCommand;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class WikiCommandTest {
    @Test
    public void prepareShouldReturnTrue() {
        // Arrange
        var commandSender = mock(CommandSender.class);
        var args = new HashMap<String, String>();
        var demoLangService = mock(DemoLanguageService.class);

        var command = new WikiCommand(commandSender, args, demoLangService);

        // Act
        boolean result = command.prepare();

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    public void executeShouldSendMessageToCommandSender() {
        // Arrange
        var messages = new ArrayList<String>() {{
           add("Expected Message 1");
           add("Expected Message 2");
           add("Expected Message 3");
           add("Expected Message 4");
        }};

        var commandSender = mock(CommandSender.class);
        var args = new HashMap<String, String>();

        var demoLangService = mock(DemoLanguageService.class);
        when(demoLangService.getWikiMessages()).thenReturn(messages);

        var command = new WikiCommand(commandSender, args, demoLangService);
        command.prepare();

        // Act
        command.execute();

        // Assert
        var expectedMessages = new String[] {
                "Expected Message 1",
                "Expected Message 2",
                "Expected Message 3",
                "Expected Message 4",};
        verify(commandSender).sendMessage(expectedMessages);
    }

}
