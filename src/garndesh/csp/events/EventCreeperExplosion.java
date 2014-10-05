package garndesh.csp.events;

import garndesh.csp.CreeperSpores;
import garndesh.csp.blocks.ModBlocks;
import garndesh.csp.lib.Strings;
import garndesh.csp.tileentity.TileEntitySpore;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventCreeperExplosion {
	private Random rand = new Random();

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void LivingAttackEvent(LivingAttackEvent event) {
		if (event.source.getEntity() instanceof EntityCreeper
				&& event.source.isExplosion()) {
			FMLLog.info("Living Attacked event attacking entity is: Creeper");
			EntityCreeper parent = (EntityCreeper) event.source.getEntity();
			World world = parent.worldObj;
			if (!world.isRemote) {
				float target = 0.1F;
				while (rand.nextFloat() >= target) {
					int x = (int) (parent.posX + rand.nextInt(10) - 5);
					int y = (int) (parent.posY + rand.nextInt(10) - 5);
					int z = (int) (parent.posZ + rand.nextInt(10) - 5);
					int block = Block.getIdFromBlock(world.getBlock(x, y, z));
					if (!world.isAirBlock(x, y, z)
							&& world.blockExists(x, y, z)
							&& world.getBlock(x, y, z).isNormalCube()
							&& Block.getIdFromBlock(ModBlocks.blockSpore) != block) {
						int meta = world.getBlockMetadata(x, y, z);
						world.setBlock(x, y, z, ModBlocks.blockSpore);
						world.setBlockMetadataWithNotify(x, y, z, meta, 2);
						TileEntitySpore tileEntity = (TileEntitySpore) world
								.getTileEntity(x, y, z);
						if (tileEntity != null)
							tileEntity.setBlockId(block);
						target += 0.1F;
					}
				}
				if (event.entity != null
						&& event.entity instanceof EntityPlayer) {
					// FMLLog.info("Attacking entity: "+event.entity.getEntityId());
					event.entity.getEntityData().setBoolean(Strings.INFECTED_TAG, true);
					CreeperSpores.instance.enforcer.addInfectedPlayer((EntityPlayer) event.entity);
				}
			}
		}
	}
}
