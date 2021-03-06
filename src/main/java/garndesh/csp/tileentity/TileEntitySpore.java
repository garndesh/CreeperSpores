package garndesh.csp.tileentity;

import garndesh.csp.blocks.ModBlocks;
import garndesh.csp.entity.EntityBabyCreeper;
import garndesh.csp.lib.Constants;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.common.FMLLog;

public class TileEntitySpore extends TileEntity {

	private static final String spawnedTag = "spawncount";
	private static final String blockIdTag = "blockId";
	private Random rand = new Random();
	private int spawned;
	private int blockId = 1;
	
	public TileEntitySpore() {
		this.spawned = 0;
	}
	
	public void setBlockId(int blockId){
		this.blockId = blockId;
	}
	
	public int getBlockId(){
		return blockId;
	}

	@Override
	public void updateEntity(){
		if(rand.nextInt(Constants.SPORE_SPAWNRATE)==0){ 
			int x = (int) (this.xCoord + rand.nextInt(10)-5);
			int y = (int) (this.yCoord + rand.nextInt(5));
			int z = (int) (this.zCoord + rand.nextInt(10)-5);
			int creepers = worldObj.getEntitiesWithinAABB(EntityCreeper.class, AxisAlignedBB.getBoundingBox(x-20, y-10, z-20, x+20, y+10, z+20)).size();
			int babys = worldObj.getEntitiesWithinAABB(EntityBabyCreeper.class, AxisAlignedBB.getBoundingBox(x-20, y-10, z-20, x+20, y+10, z+20)).size();
			
			//FMLLog.info("Trying to spawn creeper @ "+x+":"+y+":"+z);
			if(worldObj.isAirBlock(x, y, z) && worldObj.isAirBlock(x, y+1, z) && !worldObj.isRemote && creepers+babys < 15){
				EntityBabyCreeper creeper = new EntityBabyCreeper(worldObj);
				creeper.setLocationAndAngles(x+0.5, y, z+0.5, 0, 0);
				
				// The next function disables the despawning of the creeper it is not yet mapped in MCP
				//creeper.func_110163_bv();
				creeper.addPotionEffect(new PotionEffect(Potion.resistance.id, 1, 10));
				//creeper.isChild();
				worldObj.spawnEntityInWorld(creeper);
				spawned++;
				FMLLog.info(this.toString()+"spawned :"+spawned+" creepers");
				if(rand.nextInt(5)<=spawned){
					FMLLog.info("Spore exhousted");
					//
					if(blockId == 0 || blockId == Block.getIdFromBlock(ModBlocks.blockSpore)){
						worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
						worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
					}else
						worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Block.getBlockById(blockId), worldObj.getBlockMetadata(x, y, z), 2);
				}
			} 
		}
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		spawned = tag.getInteger(spawnedTag);
		blockId = tag.getInteger(blockIdTag);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger(spawnedTag, spawned);
		tag.setInteger(blockIdTag, blockId);
	}
	
	@Override 
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
		readFromNBT(pkt.func_148857_g());
	}
	
	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound nbt = new NBTTagCompound();
		  this.writeToNBT(nbt);
		  S35PacketUpdateTileEntity packet = new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
		  return packet;
	}
}
