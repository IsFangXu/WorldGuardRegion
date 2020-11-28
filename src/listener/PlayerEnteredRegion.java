package listener;

import main.Main;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class PlayerEnteredRegion implements Listener {

    @EventHandler
    public void PlayerEnteredRegion (RegionEnteredEvent event) {
        Player player = Bukkit.getPlayer(event.getUUID());
        if (player == null) return;

        List<String> datas = Main.getConfigFile().getStringList("Settings.Sound");
        for (String data : datas) {
            String soundRegionName = data.split(" ")[0];
            String sound = data.split(" ")[1];
            String soundCategory = data.split(" ")[2];

            boolean soundBoolean = ! (sound.equalsIgnoreCase("Disable")) & soundCategory.equalsIgnoreCase("Disable") ? true : false;
            boolean categoryBoolean = ! (sound.equalsIgnoreCase("Disable")) & ! (soundCategory.equalsIgnoreCase("Disable")) ? true : false;

            String regionName = event.getRegionName();
            if (regionName.equalsIgnoreCase(soundRegionName)) {
                if (sound.equalsIgnoreCase("Disable") & soundCategory.equalsIgnoreCase("Disable")) return;
                try {
                    if (soundBoolean) {
                        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.valueOf(sound), 1, 1);
                        return;
                    } else if (categoryBoolean) {
                        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.valueOf(sound), SoundCategory.valueOf(soundCategory), 1, 1);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    return;
                }
            } else {
                try {
                    if (soundBoolean) {
                        player.getPlayer().stopSound(Sound.valueOf(sound));
                        return;
                    } else if (categoryBoolean) {
                        player.getPlayer().stopSound(Sound.valueOf(sound), SoundCategory.valueOf(soundCategory));
                        return;
                    }
                    return;
                } catch (Exception e) {
                    return;
                }
            }
        }
    }
}
