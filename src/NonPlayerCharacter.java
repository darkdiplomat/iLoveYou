import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class NonPlayerCharacter {
    private OEntityPlayerMP user;
    private OEntityTrackerEntry handler;
    private Server server = iLoveYou.server;
    private World world = iLoveYou.world;
    private OMinecraftServer mcserv = server.getMCServer();
    
    public NonPlayerCharacter(String name, double x, double y, double z, float rotation, float pitch, int itemInHand) {
        user = new OEntityPlayerMP(mcserv, world.getWorld(), name, new OItemInWorldManager(world.getWorld()));
        handler = new OEntityTrackerEntry(user, 512, 1, true);
        teleportTo(x, y, z, rotation, pitch);
        if(itemInHand <= 0){
            setItemInHand(283);
        }
        else{
            setItemInHand(itemInHand);
        }
    }

    public void despawn(OEntityPlayerMP player) {
        this.handler.o.remove(player);
    }

    @SuppressWarnings("unchecked")
    public void despawnAll() {
        for (Iterator<OEntityPlayerMP> playerit = ((List<OEntityPlayerMP>)mcserv.h.b).iterator(); playerit.hasNext();) {
            OEntityPlayerMP player = playerit.next();
            this.handler.o.remove(player);
        }
    }

    @SuppressWarnings({ "unchecked" })
    public void delete() {
        for (Iterator<OEntityPlayerMP> playerit = ((List<OEntityPlayerMP>)mcserv.h.b).iterator(); playerit.hasNext();) {
            OEntityPlayerMP player = playerit.next();
            player.a.b(new OPacket29DestroyEntity(this.handler.a.bd));
        }
    }

    public void broadcast(Player player) {
        ArrayList<OEntityPlayerMP> list = new ArrayList<OEntityPlayerMP>();
        list.add(player.getUser());
        this.handler.b(list);
        this.handler.a(list);
    }

    public void broadcastToAll() {
        this.handler.b(mcserv.h.b);
        this.handler.a(mcserv.h.b);
    }

    public String getName() {
        return user.v;
    }

    public OEntityPlayerMP getUser() {
        return user;
    }

    public void setName(String name) {
        user.v = name;
    }

    public double getX() {
        return user.bm;
    }

    public void setX(double x) {
        user.bm = x;
    }

    public double getY() {
        return user.bn;
    }

    public void setY(double y) {
        user.bn = y;
    }

    public double getZ() {
        return user.bo;
    }

    public void setZ(double z) {
        user.bo = z;
    }

    public float getRotation() {
        return user.bs;
    }

    public void setRotation(float rot) {
        user.bs = rot;
    }

    public float getPitch() {
        return user.bt;
    }

    public void setPitch(float pitch) {
        user.bt = pitch;
    }

    public int getItemInHand() {
        return user.y()[0].c;
    }

    public void setItemInHand(int type) {
        user.y()[0] = new OItemStack(type, 1, 0);
    }

    public void teleportTo(double x, double y, double z, float rotation, float pitch) {
        user.b(x, y, z, rotation, pitch);
    }
}
