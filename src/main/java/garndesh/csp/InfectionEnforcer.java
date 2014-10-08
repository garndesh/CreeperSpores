package garndesh.csp;

import garndesh.csp.entity.EntityBabyCreeper;
import garndesh.csp.lib.Constants;
import garndesh.csp.lib.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class InfectionEnforcer {

	private HashMap<UUID, EntityPlayer> infected = new HashMap<UUID, EntityPlayer>();
	private Random rand = new Random();
	private List<UUID> keySet;
	private int infectionFrequency;
	private int debuffDuration;
	private int creeperSpawnFrequency;
	
	public InfectionEnforcer(){
		infectionFrequency = Constants.INFECTION_FREQUENCY;
		debuffDuration = Constants.DEBUFF_DURATION;
		creeperSpawnFrequency = Constants.INFECTION_SPAWN_FREQUENCY;
		new Thread(looper, "InfectionEnforcer").start();
	}
	
	public void addInfectedPlayer(EntityPlayer player){
		FMLLog.info("Player added: "+player.getDisplayName());
		if(!infected.containsKey(player.getUniqueID())){
			infected.put(player.getUniqueID(), player);
			keySet = new ArrayList<UUID>(infected.keySet());
		}
	}
	
	public void removeInfectedPlayer(EntityPlayer player){
		FMLLog.info("Player removed: "+player.getDisplayName());
		if(infected.containsKey(player.getUniqueID())){
			infected.remove(player.getUniqueID());
			keySet = new ArrayList<UUID>(infected.keySet());
		}
	}
	
	public void makePlayerSick(EntityPlayer player){
		switch(rand.nextInt(7)){
		case 0:
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, debuffDuration*3*20));
			break;
		case 1:
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, debuffDuration*20));
			break;
		case 2:
			player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, debuffDuration*20));
			break;
		case 3:
			player.addPotionEffect(new PotionEffect(Potion.hunger.id, debuffDuration*20));
			break;
		case 4:
			player.addPotionEffect(new PotionEffect(Potion.weakness.id, debuffDuration*20));
			break;
		case 5:
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, debuffDuration*20));
			break;
		default:
			break;
		}
		
		if(rand.nextInt(creeperSpawnFrequency)==0){
			World world = player.worldObj;
			int x = ((int) player.posX) + rand.nextInt(10)-5;
			int y = ((int) player.posY) + rand.nextInt(10)-5;
			int z = ((int) player.posZ) + rand.nextInt(10)-5;
			if(world.isAirBlock(x, y, z)&&world.isAirBlock(x, y+1, z)){
				EntityBabyCreeper creeper = new EntityBabyCreeper(world);
				creeper.setPositionAndRotation(x+0.5, y+0.5, z+0.5, 0, 0);
				creeper.addPotionEffect(new PotionEffect(Potion.invisibility.id, 2*60*20, 1));
				world.spawnEntityInWorld(creeper);
			}
		}
		if(rand.nextInt(4*creeperSpawnFrequency)==0){
			player.getEntityData().setBoolean(Strings.INFECTED_TAG, false);
		}
	}
	
	private Runnable looper = new Runnable(){

		@Override
		public void run() {
			while(true){
				try{
					if(infected!=null && infected.size() != 0){
						int infectedCount = infected.size();
						UUID key = keySet.get(rand.nextInt(keySet.size()));
						Thread.sleep(Math.max(infectionFrequency*1000/infectedCount, 100));
						makePlayerSick(infected.get(key));
					} else{
						//FMLLog.info("No infected registered waiting a turn for player to get infected");
						Thread.sleep(infectionFrequency*1000);
					}
				} catch(InterruptedException ex){
					Thread.currentThread().interrupt();
					break;
				}
			}
		}
		
	};
}
