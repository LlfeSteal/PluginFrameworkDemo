package unit.command;

import fr.lifesteal.pluginframework.demo.business.contract.DemoLanguageService;
import fr.lifesteal.pluginframework.demo.command.PingCommand;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
public class PingCommandTest {
    @Test
    public void prepareShouldReturnTrue() {
        // Arrange
        var commandSender = mock(CommandSender.class);
        var args = new HashMap<String, String>();
        var demoLangService = mock(DemoLanguageService.class);

        var command = new PingCommand(commandSender, args, demoLangService);

        // Act
        boolean result = command.prepare();

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    public void executeShouldSendMessageToCommandSender() {
        // Arrange
        var expectedMessage = "Expected message";

        var commandSender = mock(CommandSender.class);
        var args = new HashMap<String, String>();

        var demoLangService = mock(DemoLanguageService.class);
        when(demoLangService.getPongMessage()).thenReturn(expectedMessage);

        var command = new PingCommand(commandSender, args, demoLangService);
        command.prepare();

        // Act
        command.execute();

        // Assert
        verify(commandSender).sendMessage(expectedMessage);
    }
}
