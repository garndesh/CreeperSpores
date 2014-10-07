package garndesh.csp.lib;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Constants {

	private static final String INFECTION_SETTINGS = "Infection Settings";
	private static final int INFECTION_FREQUENCY_DEFAULT = 60;
	private static final int DEBUFF_DURATION_DEFAULT = 4;
	private static final int INFECTION_SPAWN_FREQUENCY_DEFAULT = 20;
	
	private static final String CURE_SETTINGS = "cure settings";
	private static final int INFECTION_BADNESS_DEFAULT = 3;
	
	public static int INFECTION_FREQUENCY;
	public static int DEBUFF_DURATION;
	public static int INFECTION_SPAWN_FREQUENCY;
	
	public static int CURE_BADNESS;
	
	public static void init(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		INFECTION_FREQUENCY = config.get(INFECTION_SETTINGS, "Infection Frequency", INFECTION_FREQUENCY_DEFAULT).getInt();
		DEBUFF_DURATION = config.get(INFECTION_SETTINGS, "Debuff Duration", DEBUFF_DURATION_DEFAULT).getInt();
		INFECTION_SPAWN_FREQUENCY = config.get(INFECTION_SETTINGS, "Infection spawn frequency", INFECTION_SPAWN_FREQUENCY_DEFAULT).getInt();
		
		CURE_BADNESS = config.getInt(CURE_SETTINGS, CURE_SETTINGS, INFECTION_BADNESS_DEFAULT, 1, 5, "How bad the cure is for you. Higher is worse");
		config.save();
	}
}
