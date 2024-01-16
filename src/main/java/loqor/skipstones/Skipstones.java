package loqor.skipstones;

import loqor.skipstones.core.SkipEntityTypes;
import loqor.skipstones.core.SkipItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Skipstones implements ModInitializer {
	public static final String MOD_ID = "skipstones";

	public static final ItemGroup SKIPSTONES_ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(SkipItems.SKIPPING_STONE))
			.displayName(Text.translatable("itemGroup.skipstones.item_group"))
			.entries((context, entries) -> {
				entries.add(SkipItems.SKIPPING_STONE);
			})
			.build();

	//public static final NeptuneItemGroup SKIPSTONES_ITEM_GROUP = new NeptuneItemGroup(new Identifier(MOD_ID, "item_group"), new ItemStack(SkipItems.SKIPPING_STONE));
    public static final Logger LOGGER = LoggerFactory.getLogger("skipstones");

	@Override
	public void onInitialize() {
		LOGGER.info("Skipstones is loaded!");

		SkipItems.initialize();
		SkipEntityTypes.initialize();

		Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "item_group"), SKIPSTONES_ITEM_GROUP);
		//NeptuneInitHandler.register(SkipItems.class, MOD_ID);
		//SKIPSTONES_ITEM_GROUP.onInitialize();
	}
}