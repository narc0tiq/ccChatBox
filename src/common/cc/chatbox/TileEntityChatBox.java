package cc.chatbox;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;

import dan200.computer.api.IComputerAccess;
import dan200.computer.api.ILuaContext;
import dan200.computer.api.IPeripheral;

public class TileEntityChatBox extends TileEntity implements IPeripheral {
    public List<IComputerAccess> connectedComputers = new ArrayList<IComputerAccess>();

    public TileEntityChatBox() {
        super();
    }

// public interface IPeripheral {
    // callMethod uses reflection to call methods named peripheralMethods[i] +
    // "Peripheral", e.g. "sayPeripheral". These methods must take the
    // parameters (IComputerAccess, Object[]), and return an Object[]
    public static String[] peripheralMethods = new String[] { "say", "tell" };

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
        return peripheralMethods;
    }

    @Override
    public Object[] callMethod(IComputerAccess computer, ILuaContext context,
                               int index, Object[] arguments) throws Exception {
        Method method = null;
        try {
            // Find method like, e.g., "sayPeripheral(IComputerAccess, Object[])"
            method = this.getClass().getMethod(peripheralMethods[index] + "Peripheral", IComputerAccess.class, Object[].class);
        }
        catch(NoSuchMethodException e) {
            throw new Exception("Oops! The peripheral method '" + peripheralMethods[index] + "' " +
                                "doesn't seem to have been written yet!", e);
        }

        return (Object[]) method.invoke(this, new Object[]{computer, arguments});
    }

    @Override public void attach(IComputerAccess computer) {
        System.out.println("Attached to computer " + computer.getID() + " on side " + computer.getAttachmentName());
    }

    @Override public void detach(IComputerAccess computer) {
        System.out.println("Detached from computer " + computer.getID() + " on side " + computer.getAttachmentName());
    }
// }

    public ChatMessageComponent makeMessage(IComputerAccess computer, String text) {
        String identifier = String.format("[Computer %d]", computer.getID());
        ChatMessageComponent message = ChatMessageComponent.createFromText(identifier);
        message.addText(" ");
        message.addText(text);

        return message;
    }

    public Object[] sayPeripheral(IComputerAccess computer, Object[] args) throws Exception {
        if(args.length < 1 || !(args[0] instanceof String)) {
            throw new Exception("Invalid arguments: need just one string (the chat message to send).");
        }

        ChatBox.proxy.chatToAll(makeMessage(computer, (String)args[0]));
        return new Object[0];
    }

    public Object[] tellPeripheral(IComputerAccess computer, Object[] args) throws Exception {
        if(args.length < 2 || !(args[0] instanceof String) || !(args[1] instanceof String)) {
            throw new Exception("Invalid arguments: need player and message (two strings).");
        }

        ChatBox.proxy.chatToPlayer((String)args[0], makeMessage(computer, (String)args[1]));
        return new Object[0];
    }
}
