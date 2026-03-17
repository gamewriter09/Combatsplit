package me.server.combat;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.geysermc.floodgate.api.FloodgateApi;

public class CombatPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (FloodgateApi.getInstance().isFloodgatePlayer(p.getUniqueId())) {
            p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(1024);
        } else {
            p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4.0);
        }
    }

    @EventHandler
    public void onHeal(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player p) {
            if (FloodgateApi.getInstance().isFloodgatePlayer(p.getUniqueId())) {
                p.setExhaustion(0.0f);
            }
        }
    }
                           }
