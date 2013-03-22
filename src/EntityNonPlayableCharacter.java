import java.net.InetSocketAddress;
import java.net.SocketAddress;

public final class EntityNonPlayableCharacter extends OEntityPlayerMP{

    public EntityNonPlayableCharacter(String name, World world, double x, double y, double z, float rotation, float pitch){
        super(etc.getMCServer(), world.getWorld(), name, new OItemInWorldManager(world.getWorld()));
        this.a = new NPCServHand(this);
        this.a(x, y, z, rotation, pitch - 90);
    }

    /**
     * Override the ONetServerHandler for NonPlayableCharacters.
     * 
     * @author Jason (darkdiplomat)
     */
    private final static class NPCServHand extends ONetServerHandler{

        final static NetManChu herp = new NetManChu();

        public NPCServHand(EntityNonPlayableCharacter npc){
            super(etc.getMCServer(), herp, npc);
        }

        public void d(){}

        public void c(String s){}

        public void a(OPacket10Flying opacket10flying){}

        public void a(double d0, double d1, double d2, float f, float f1){
            this.c.a(d0, d1, d2, f, f1);
        }

        public void a(OPacket14BlockDig opacket14blockdig){}

        public void a(OPacket15Place opacket15place){}

        public void a(String s, Object[] aobject){}

        public void a(OPacket opacket){}

        public void b(OPacket opacket){}

        public void a(OPacket16BlockItemSwitch opacket16blockitemswitch){}

        public void a(OPacket3Chat opacket3chat){}

        public void a(OPacket18Animation opacket18animation){}

        public void a(OPacket19EntityAction opacket19entityaction){}

        public void a(OPacket255KickDisconnect opacket255kickdisconnect){}

        public int e(){
            return -1;
        }

        public void a(OPacket7UseEntity opacket7useentity){}

        public void a(OPacket205ClientCommand opacket205clientcommand){}

        public boolean b(){
            return true;
        }

        public void a(OPacket9Respawn opacket9respawn){}

        public void a(OPacket101CloseWindow opacket101closewindow){}

        public void a(OPacket102WindowClick opacket102windowclick){}

        public void a(OPacket108EnchantItem opacket108enchantitem){}

        public void a(OPacket107CreativeSetSlot opacket107creativesetslot){}

        public void a(OPacket106Transaction opacket106transaction){}

        public void a(OPacket130UpdateSign opacket130updatesign){}

        public void a(OPacket0KeepAlive opacket0keepalive){}

        public boolean a(){
            return true;
        }

        public void a(OPacket202PlayerAbilities opacket202playerabilities){}

        public void a(OPacket203AutoComplete opacket203autocomplete){}

        public void a(OPacket204ClientInfo opacket204clientinfo){}

        public void a(OPacket250CustomPayload opacket250custompayload){}

        public Player getPlayer(){
            return null;
        }

        public void setPlayer(OEntityPlayerMP oentityplayermp){}

        public void setPlayer(Player player){}

        public void msg(String msg){}
    }

    /**
     * Overrides the OINetworkManager of NonPlayableCharacters
     * 
     * @author Jason (darkdiplomat)
     */
    private final static class NetManChu implements OINetworkManager{

        private final SocketAddress herp = new InetSocketAddress("127.0.0.1", 0);

        @Override
        public void a(){}

        @Override
        public void a(ONetHandler arg0){}

        @Override
        public void a(OPacket arg0){}

        @Override
        public void a(String arg0, Object... arg1){}

        @Override
        public void b(){}

        @Override
        public SocketAddress c(){
            return herp;
        }

        @Override
        public void d(){}

        @Override
        public int e(){
            return 0;
        }
    }
}
