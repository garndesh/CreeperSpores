package garndesh.csp.items;

import garndesh.csp.CreeperSpores;
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
            player.addPotionEffect(new PotionEffect(Potion.hunger.id, 2400, 0));
            player.addPotionEffect(new PotionEffect(Potion.weakness.id, 1200, 0));
            player.addPotionEffect(new PotionEffect(Potion.poison.id, 600, 0));
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 0));
            player.getEntityData().setBoolean(Strings.INFECTED_TAG, false);
            CreeperSpores.instance.enforcer.removeInfectedPlayer(player);
        }
    }
}
