package garndesh.csp.blocks;

import garndesh.csp.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {
	public static Block blockSpore; 
	
	public static void init(){
		blockSpore = new BlockSpore();
		

		GameRegistry.registerBlock(blockSpore, "block."+Strings.BLOCK_SPORE_NAME);
	}
}
