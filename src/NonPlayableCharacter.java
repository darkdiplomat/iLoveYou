import java.lang.reflect.Field;

public class NonPlayableCharacter extends HumanEntity{
    EntityNonPlayableCharacter npc;

    public NonPlayableCharacter(String name, World world, double x, double y, double z, float rotation, float pitch, Item itemInHand){
        try {
            npc = new EntityNonPlayableCharacter(name, world, x, y, z, rotation, pitch);
            // Inject our EntityNonPlayableCharacter class into BaseEntity.entity
            Field f = BaseEntity.class.getDeclaredField("entity");
            f.setAccessible(true);
            f.set(this, npc);
            //
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        setInventorySlot(0, itemInHand);
        setInHandSlot(0);
    }

    public NonPlayableCharacter(EntityNonPlayableCharacter npc){
        this.npc = npc;
    }

    public void setInventorySlot(int slot, Item item){
        if (slot < 0 || slot > 39) {
            return;
        }
        if (slot > 35) {
            this.npc.bK.b[slot - 36] = item.getBaseItem(); // Armor Slots
        }
        else {
            this.npc.bK.a[slot] = item.getBaseItem(); // Normal Slots
        }
    }

    public void setInHandSlot(int slot){
        if (slot < 0 || slot > 9) {
            return;
        }
        this.npc.bK.c = slot;
    }

    public void lookat(Player player){
        double xDiff = player.getX() - getX();
        double yDiff = player.getY() - getY();
        double zDiff = player.getZ() - getZ();
        double DistanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        double DistanceY = Math.sqrt(DistanceXZ * DistanceXZ + yDiff * yDiff);
        double yaw = (Math.acos(xDiff / DistanceXZ) * 180 / Math.PI);
        double pitch = (Math.acos(yDiff / DistanceY) * 180 / Math.PI) - 90;
        if (zDiff < 0.0) {
            yaw = yaw + (Math.abs(180 - yaw) * 2);
        }
        teleportTo(getX(), getY(), getZ(), (float) yaw - 90, (float) pitch);
        npc.bR = (float) yaw - 90; // Camera/Head Position
        npc.c(); // Update Entity
    }
}
