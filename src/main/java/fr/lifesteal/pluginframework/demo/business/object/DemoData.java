package fr.lifesteal.pluginframework.demo.business.object;

import fr.lifesteal.pluginframework.core.config.ConfigModel;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.Map;

@SerializableAs("DemoData")
public class DemoData extends ConfigModel {
    private int id;
    private String name;

    public DemoData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DemoData(Map<String, Object> args) {
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
        return new DemoData(this.id, this.name);
    }
}
