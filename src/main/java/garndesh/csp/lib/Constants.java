package garndesh.csp.lib;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Constants {

	private static final String MISC_SETTINGS = "Misc settings";
	private static final String INFECTION_SETTINGS = "Infection Settings";
	private static final int INFECTION_FREQUENCY_DEFAULT = 60;
	private static final int DEBUFF_DURATION_DEFAULT = 4;
	private static final int INFECTION_SPAWN_FREQUENCY_DEFAULT = 20;
	
	private static final int INFECTION_BADNESS_DEFAULT = 3;
	
	private static final int CREEPER_CHILD_TIME_DEFAULT = 20*60*15;

	private static final int SPORE_SPAWNRATE_DEFAULT = 20*60*5;
	private static final int IMMUME_DURATION_DEFAULT = 10;
	
	public static int INFECTION_FREQUENCY;
	public static int DEBUFF_DURATION;
	public static int INFECTION_SPAWN_FREQUENCY;
	
	public static int CURE_BADNESS;
	public static int CREEPER_CHILD_TIME;
	public static int SPORE_SPAWNRATE;
	public static int IMMUME_DURATION; 
	
	public static void init(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		INFECTION_FREQUENCY = config.get(INFECTION_SETTINGS, "Infection Frequency", INFECTION_FREQUENCY_DEFAULT).getInt();
		DEBUFF_DURATION = config.get(INFECTION_SETTINGS, "Debuff Duration", DEBUFF_DURATION_DEFAULT).getInt();
		INFECTION_SPAWN_FREQUENCY = config.get(INFECTION_SETTINGS, "Infection spawn frequency", INFECTION_SPAWN_FREQUENCY_DEFAULT).getInt();
		
		CURE_BADNESS = config.getInt("Cure badness", MISC_SETTINGS, INFECTION_BADNESS_DEFAULT, 1, 5, "How bad the cure is for you. Higher is worse");
		CREEPER_CHILD_TIME = config.getInt("Creeper child time", MISC_SETTINGS, CREEPER_CHILD_TIME_DEFAULT, 0, 20*60*60, "The minimum time a baby creeper stays a baby in ticks");
		SPORE_SPAWNRATE = config.getInt("Spore spawn time", MISC_SETTINGS, SPORE_SPAWNRATE_DEFAULT, 0, 20*60*60, "The avarage time it takes for a spore to spawn a baby creeper in ticks");
		IMMUME_DURATION = config.getInt("Immume duration", MISC_SETTINGS, IMMUME_DURATION_DEFAULT, 0, 100, "The number of explosions you can't become infected after eating the cure");
		
		
		config.save();
	}
}
