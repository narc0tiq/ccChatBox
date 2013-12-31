package cc.chatbox;

import java.util.List;
import java.util.ArrayList;

public class ClientProxy extends CommonProxy {
    @Override
    public List<String> getCurrentPlayers() {
        // makes no sense on the client side
        return new ArrayList<String>();
    }
}
