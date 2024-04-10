package unit.command;

import fr.lifesteal.pluginframework.demo.business.contract.DemoConfigurationService;
import fr.lifesteal.pluginframework.demo.business.object.DemoData;
import fr.lifesteal.pluginframework.demo.command.DisplayDataCommand;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DisplayDataCommandTest {

    @Test
    public void prepareShouldReturnTrue() {
        // Arrange
        var commandSender = mock(CommandSender.class);
        var args = new HashMap<String, String>();
        var demoConfigService = mock(DemoConfigurationService.class);

        var command = new DisplayDataCommand(commandSender, args, demoConfigService);

        // Act
        boolean result = command.prepare();

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    public void executeShouldSendMessageToCommandSender() {
        // Arrange
        var data = new ArrayList<DemoData>() {{
            add(new DemoData(99, "Name99"));
            add(new DemoData(199, "Name199"));
            add(new DemoData(299, "Name299"));
        }};
        var commandSender = mock(CommandSender.class);
        var args = new HashMap<String, String>();

        var demoConfigService = mock(DemoConfigurationService.class);
        when(demoConfigService.getDemoData()).thenReturn(data);

        var command = new DisplayDataCommand(commandSender, args, demoConfigService);

        // Act
        command.execute();

        // Assert
        String[] expectedMessage = new String[]{"99 - Name99", "199 - Name199", "299 - Name299"};
        verify(commandSender, times(1)).sendMessage(expectedMessage);
    }
}
