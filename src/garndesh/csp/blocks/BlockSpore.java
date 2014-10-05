package garndesh.csp.blocks;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import garndesh.csp.tileentity.TileEntitySpore;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpore extends Block implements ITileEntityProvider {

	public BlockSpore() {
		super(Material.grass);
		setHardness(2);
		setStepSound(soundTypeGrass);
		setResistance(250);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySpore();
	}
	
	@Override
    public boolean isOpaqueCube(){
            return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int meta)
    {
		World worldObj = world.getTileEntity(x, y, z).getWorldObj();
		TileEntitySpore tileEntity = (TileEntitySpore) worldObj.getTileEntity(x, y, z);
		int blockId = tileEntity.getBlockId();
		//FMLLog.info("Block Name: "+name+" from tileEntity "+tileEntity.toString());
		if(blockId!=0){
			Block block = Block.getBlockById(blockId);
			if(block!=null){
				return block.getIcon(meta, world.getBlockMetadata(x, y, z));
			} else {
				FMLLog.info("No block found for ID: "+blockId);
			}
		}
			
        return this.getIcon(meta, world.getBlockMetadata(x, y, z));
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = Blocks.grass.getBlockTextureFromSide(1);
    }
	
	@Override
	public int colorMultiplier(IBlockAccess world, int x, int y, int z){
		TileEntitySpore te = (TileEntitySpore) world.getTileEntity(x, y, z);
		if(world.getBlock(x, y, z) == Block.getBlockById(te.getBlockId()) || Block.getBlockById(te.getBlockId()) == Blocks.grass )
			return Blocks.grass.colorMultiplier(world, x, 1, z);
		return 0;
	}
}
