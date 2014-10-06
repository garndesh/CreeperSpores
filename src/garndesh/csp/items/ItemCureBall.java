package garndesh.csp.items;

import java.util.ArrayList;
import java.util.List;

import garndesh.csp.CreeperSpores;
import garndesh.csp.lib.Constants;
import garndesh.csp.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCureBall extends ItemFood {

	private PotionEffect hunger, weakness, poison, regen, digSlow, moveSlow, confusion, blindness, wither;
	private int badness;
	
	public ItemCureBall() {
		super(64, 0, false);
		super.setAlwaysEdible();
		setUnlocalizedName(Strings.RECOURCE_PREFIX+Strings.ITEM_CUREBALL_NAME);

    	badness = Constants.CURE_BADNESS;
    	float multiplyer = badness/3;
		
		hunger = new PotionEffect(Potion.hunger.id, (int) (2400*multiplyer), 0);
        weakness = new PotionEffect(Potion.weakness.id, (int) (1200*multiplyer), 0);
        poison = new PotionEffect(Potion.poison.id, (int) (600*multiplyer), 0);
        regen = new PotionEffect(Potion.regeneration.id, (int) (600*multiplyer), 0);
        digSlow = new PotionEffect(Potion.digSlowdown.id, (int) (600*multiplyer), 1);
        moveSlow = new PotionEffect(Potion.moveSlowdown.id, (int) (600*multiplyer), 1);
        confusion = new PotionEffect(Potion.confusion.id, (int) (600*multiplyer), 0);
        blindness = new PotionEffect(Potion.blindness.id, (int) (600*multiplyer), 0);
        wither = new PotionEffect(Potion.wither.id, (int) (600*multiplyer), 0);
        
        if(badness>=3){
        	List<ItemStack> item = new ArrayList<ItemStack>();
			hunger.setCurativeItems(item);
	        weakness.setCurativeItems(item);
	        poison.setCurativeItems(item);
	        regen.setCurativeItems(item);
	        digSlow.setCurativeItems(item);
	        moveSlow.setCurativeItems(item);
	        confusion.setCurativeItems(item);
	        blindness.setCurativeItems(item);
	        wither.setCurativeItems(item);
        }
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("csp:cureball");
    }
	
	@Override
	protected void onFoodEaten(ItemStack item, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            player.addPotionEffect(hunger);
            player.addPotionEffect(weakness);
            if(badness>=3){
	            player.addPotionEffect(poison);
	            player.addPotionEffect(regen);
            }
            if(badness>=4){
	            player.addPotionEffect(digSlow);
	            player.addPotionEffect(moveSlow);
            }
            if(badness>=5){
	            player.addPotionEffect(confusion);
	            player.addPotionEffect(blindness);
	            player.addPotionEffect(wither);
            }
	        player.getEntityData().setBoolean(Strings.INFECTED_TAG, false);
            CreeperSpores.instance.enforcer.removeInfectedPlayer(player);
        }
    }
}
