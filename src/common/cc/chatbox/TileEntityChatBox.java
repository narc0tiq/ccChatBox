package cc.chatbox;

import java.util.List;
import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;

import dan200.computer.api.IComputerAccess;
import dan200.computer.api.ILuaContext;
import dan200.computer.api.IPeripheral;

public class TileEntityChatBox extends TileEntity implements IPeripheral {
    public List<IComputerAccess> connectedComputers = new ArrayList<IComputerAccess>();

    public TileEntityChatBox() {
        super();
    }

// public interface IPeripheral {
    @Override
    public String getType() {
        return "chatbox";
    }

    @Override
    public boolean canAttachToSide(int side) {
        return true;
    }

    @Override
    public String[] getMethodNames() {
        return new String[0];
    }

    @Override
    public Object[] callMethod(IComputerAccess computer, ILuaContext context,
                               int method, Object[] arguments) throws Exception {
        return new Object[0];
    }

    @Override public void attach(IComputerAccess computer) {
        System.out.println("Attached to computer " + computer.getID() + " on side " + computer.getAttachmentName());
    }

    @Override public void detach(IComputerAccess computer) {
        System.out.println("Detached to computer " + computer.getID() + " on side " + computer.getAttachmentName());
    }
// }
}
