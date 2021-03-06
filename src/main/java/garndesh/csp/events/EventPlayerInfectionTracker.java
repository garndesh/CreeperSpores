package garndesh.csp.events;

import garndesh.csp.CreeperSpores;
import garndesh.csp.lib.Strings;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class EventPlayerInfectionTracker {

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event){
		FMLLog.info("Player logged in.");
		if(event.player.getEntityData().getBoolean(Strings.INFECTED_TAG)){
			CreeperSpores.instance.enforcer.addInfectedPlayer(event.player);
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void PlayerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event){
		if(event.player.getEntityData().getBoolean(Strings.INFECTED_TAG)){
			CreeperSpores.instance.enforcer.removeInfectedPlayer(event.player);
		}
	}
}
