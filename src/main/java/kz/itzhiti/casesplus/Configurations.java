package kz.itzhiti.casesplus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Configurations {

    public static FileConfiguration getFile(final String fileName) {
        final File file = new File(CasesPlus.getInstance().getDataFolder(), fileName);
        if (CasesPlus.getInstance().getResource(fileName) == null) {
            try {
                YamlConfiguration.loadConfiguration(file).save(file);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return YamlConfiguration.loadConfiguration(file);
        }
        if (!file.exists()) {
            CasesPlus.getInstance().saveResource(fileName, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }
}
