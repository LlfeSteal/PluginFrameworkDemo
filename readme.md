# PluginFrameworkDemo
This is merely a project demonstrating the functionalities of a **Spigot** plugin development framework that I am currently working on.

Main goal is to streamline the entire infrastructure aspect of development, allowing the developer to focus on the real business features that deliver value.

# Features
There are currently supported features. 

## Easier command creation
One line command declaration and registration in 2 steps : 

Command executor declaration : 

```java
public class MyCommand extends CommandExecutor {
    
    private final String message;
    
    public MyCommand(CommandSender issuer, String[] args, String message) {
        super(issuer, args);
        this.message = message;
    }

    @Override
    public boolean prepare() {
        return true;
    }

    @Override
    public boolean execute() {
        getIssuer().sendMessage(this.message);
        return true;
    }
}
```

Declaration and registration : 
```java
@Override
protected List<PluginCommand> registerCommands() {
    return new ArrayList<>() {{
        add(getPluginCommandFactory()
                .setName("mycommand")
                .setDescription("My new command description.")
                .addAlias("myalias")
                .setDefaultCommand(getCommandBaseBuilder()
                        .setPermission("mycommand.permission")
                        .setExecutorType(MyCommand.class)
                        .addExtraArgument("Mon message")
                        .build())
                .build());
    }};
}
```
Inject objects like business services by adding ExtraArguments.

## Easier configuration management
Automatic configuration file création.

Create a YAML config file called "myconfig.yml" in a subfolder called "subfolder"

```java
var myConfigurationRepository = getConfigRepositoryFactory().getNewYamlRepository("subfolder", "myconfig.yml");
```
Will create a new file located at : ```[pluginFolder]/subfolder/myconfig.yml```

Configuration service allow to declare key, type and default value for configuration parameter.

```java
public class MyConfigService extends ConfigServiceBase {
    
    @Colorized
    @ConfigParam(paramKey = "messages")
    private List<String> messages = new ArrayList<>() {{
        add("&eThere are our colorized messages : ");
        add("&aMessage 1 green");
        add("&3Message2 blue");
        add("&4Message 3 red");
        add("&6Message 4 orange");
    }};

    public MyConfigService(Logger logger, ConfigRepository configRepository) {
        super(logger, configRepository);
    }
}
```
@Colorized annotation will apply minecraft colors to String and elements of List<String>.

Configuration service registration
```java
@Override
protected List<ConfigService> registerConfigurationsServices() {
    return new ArrayList<>() {{
        add(new MyConfigService(...));
    }};
}
```

Complex objects are supported but extra code is needed.

Model delcaration : 
```java
public class MyCustomModel extends ConfigModel {
    private int id;
    private String name;

    public MyCustomModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MyCustomModel(Map<String, Object> args) {
        super(args);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public ConfigModel clone() {
        return new MyCustomModel(this.id, this.name);
    }
}
```

Deserialization declaration :
```java
static {
    ConfigurationSerialization.registerClass(MyCustomModel.class);
}
```



JSON Support is coming soon...

## 

