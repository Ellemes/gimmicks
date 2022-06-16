package ellemes.gimmicks.impl.item;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class BuildersSatchelMenu extends AbstractContainerMenu {
    protected BuildersSatchelMenu(@Nullable MenuType<?> menuType, int id, Inventory playerInventory, int satchelSlotIndex) {
        super(menuType, id);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
