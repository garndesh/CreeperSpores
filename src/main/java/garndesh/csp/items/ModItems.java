package garndesh.csp.items;

import garndesh.csp.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

	public static ItemCureBall itemCureBall;
	
	public static void init(){
		itemCureBall = new ItemCureBall();
		
		//Register Items with forge
		GameRegistry.registerItem(itemCureBall, "item."+Strings.ITEM_CUREBALL_NAME);
	}
}
