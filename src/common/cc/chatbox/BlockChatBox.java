package cc.chatbox;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockChatBox extends BlockContainer {
    public BlockChatBox(int id, Material material) {
        super(id, material);

        this.setUnlocalizedName(CommonProxy.NAME_CHAT_BOX);
        this.setHardness(1.0f);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    public BlockChatBox(int id) {
        this(id, Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityChatBox();
    }
}
