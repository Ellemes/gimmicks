package ellemes.gimmicks.impl.item;

import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BuildersSatchel extends Item {
    public BuildersSatchel(Properties properties) {
        super(properties);
    }

    // Right-click on a satchel to view its contents
    @Override
    public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot, ClickAction click, Player player, SlotAccess slotAccess) {
        if (other.isEmpty() && click == ClickAction.SECONDARY) {
            // Open Satchel
            return true;
        }
        return false;
    }

    // Allow placing blocks into satchel by right-clicking it on a block.
    @Override
    public boolean overrideStackedOnOther(ItemStack self, Slot slot, ClickAction click, Player player) {
        if (slot.hasItem() && click == ClickAction.SECONDARY) {
            // Add item to satchel
            return true;
        }
        return false;
    }
}
