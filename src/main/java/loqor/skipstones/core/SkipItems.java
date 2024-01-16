package loqor.skipstones.core;

import loqor.skipstones.Skipstones;
import loqor.skipstones.core.items.SkippingStoneItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SkipItems {

    public static final Item SKIPPING_STONE = new SkippingStoneItem(new FabricItemSettings());

    public static void initialize() {
        Registry.register(Registries.ITEM, new Identifier(Skipstones.MOD_ID, "skipping_stone"), SKIPPING_STONE);
    }
    
}
