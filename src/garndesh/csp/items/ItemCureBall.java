package garndesh.csp.items;

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

	public ItemCureBall() {
		super(64, 0, false);
		super.setAlwaysEdible();
		setUnlocalizedName(Strings.RECOURCE_PREFIX+Strings.ITEM_CUREBALL_NAME);
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
        	int badness = Constants.CURE_BADNESS;
        	float multiplyer = badness/3;
            player.addPotionEffect(new PotionEffect(Potion.hunger.id, (int) (2400*multiplyer), 0));
            player.addPotionEffect(new PotionEffect(Potion.weakness.id, (int) (1200*multiplyer), 0));
            if(badness>=3){
	            player.addPotionEffect(new PotionEffect(Potion.poison.id, (int) (600*multiplyer), 0));
	            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, (int) (600*multiplyer), 0));
            }
            if(badness>=4){
	            player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, (int) (600*multiplyer), 2));
	            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, (int) (600*multiplyer), 2));
            }
            if(badness>=5){
	            player.addPotionEffect(new PotionEffect(Potion.confusion.id, (int) (600*multiplyer), 0));
	            player.addPotionEffect(new PotionEffect(Potion.blindness.id, (int) (600*multiplyer), 0));
            }
	        player.getEntityData().setBoolean(Strings.INFECTED_TAG, false);
            CreeperSpores.instance.enforcer.removeInfectedPlayer(player);
        }
    }
}
