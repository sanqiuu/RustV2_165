package com.sanqiu.rustv2.runnable;

import com.sanqiu.rustv2.model.SupplyBox;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class SupplyBoxUpdater extends BukkitRunnable {
    @Override
    public void run() {
        SupplyBox.update();
    }
}
