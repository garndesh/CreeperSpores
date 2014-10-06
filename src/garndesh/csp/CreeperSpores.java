package garndesh.csp;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import garndesh.csp.blocks.ModBlocks;
import garndesh.csp.events.EventCreeperExplosion;
import garndesh.csp.events.EventPlayerInfectionTracker;
import garndesh.csp.items.ModItems;
import garndesh.csp.lib.Reference;
import garndesh.csp.lib.Strings;
import garndesh.csp.tileentity.TileEntitySpore;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME)
public class CreeperSpores {

	@Instance(Reference.MOD_ID)
	public static CreeperSpores instance;

	public InfectionEnforcer enforcer;
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {

	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		FMLLog.info("Incubating Creeper spores....");
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		//Event registration
		//MinecraftForge.EVENT_BUS.register(new EventPlayerInfectionTracker());
		MinecraftForge.EVENT_BUS.register(new EventCreeperExplosion());
		FMLCommonHandler.instance().bus().register(new EventPlayerInfectionTracker());
		
		//register new blocks
		ModBlocks.init();
		//And items
		ModItems.init();
		
		//TileEntity registration
		GameRegistry.registerTileEntity(TileEntitySpore.class, Strings.TILE_ENTITY_SPORE);
		
		//Recipe registration
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemCureBall, 1), new ItemStack(Items.rotten_flesh), new ItemStack(Items.blaze_powder), new ItemStack(Items.gunpowder), new ItemStack(Blocks.red_mushroom));
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		enforcer = new InfectionEnforcer();
	}

}
