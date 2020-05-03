package net.llamagames.AreaProtection.listener;

import cn.nukkit.block.Block;
import cn.nukkit.entity.mob.EntityMob;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityExplodeEvent;
import cn.nukkit.event.entity.EntitySpawnEvent;
import net.llamagames.AreaProtection.AreaProtection;
import net.llamagames.AreaProtection.utils.Area;

import java.util.ArrayList;
import java.util.List;

public class EntityListener implements Listener {

    private AreaProtection plugin;

    public EntityListener(AreaProtection plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMobSpawn(EntitySpawnEvent event) {
        Area area = plugin.getAreaByPos(event.getPosition());
        if (area != null) {
            if (event.getEntity() instanceof EntityMob && !area.isAllowed("mob-spawn")) {
                event.getEntity().close();
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onExplosion(EntityExplodeEvent event) {
        List<Block> allowedBlock = new ArrayList<>();
        event.getBlockList().forEach((block) -> {
            Area explosionArea = AreaProtection.instance.getAreaByPos(block.getLocation());
            if (explosionArea != null) {
                if (explosionArea.isAllowed("explosion")) allowedBlock.add(block);
            } else allowedBlock.add(block);
        });
        event.setBlockList(allowedBlock);
    }

}