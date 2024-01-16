package loqor.skipstones.core.datagen;

import loqor.skipstones.Skipstones;
import loqor.skipstones.core.SkipItems;
import loqor.skipstones.core.datagen.datagen_providers.SkipstonesLanguageProvider;
import loqor.skipstones.core.datagen.datagen_providers.SkipstonesRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class SkipstonesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		generateUSLang(pack);
		generateRecipes(pack);
		//generateSoundData(pack);
	}

	private void generateUSLang(FabricDataGenerator.Pack pack) {
		pack.addProvider((output, registriesFuture) -> addEnglishTranslations(output, registriesFuture, LanguageType.EN_US));
	}

	private void generateRecipes(FabricDataGenerator.Pack pack) {
		pack.addProvider((output, registriesFuture) -> {
			SkipstonesRecipeProvider provider = new SkipstonesRecipeProvider(output);
			provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, SkipItems.SKIPPING_STONE)
					.pattern("BBB")
					.pattern("BSB")
					.pattern("BBB")
					.input('S', Items.STONE)
					.input('B', Items.STONE_BUTTON)
					.criterion(FabricRecipeProvider.hasItem(Items.STONE), FabricRecipeProvider.conditionsFromItem(Items.STONE))
					.criterion(FabricRecipeProvider.hasItem(Items.STONE), FabricRecipeProvider.conditionsFromItem(Items.STONE_BUTTON))
			);
			return provider;
		});
	}

	private SkipstonesLanguageProvider addEnglishTranslations(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, LanguageType languageType) {
		SkipstonesLanguageProvider provider = new SkipstonesLanguageProvider(output, languageType);
		provider.addTranslation(SkipItems.SKIPPING_STONE, "Skipping Stone");
		provider.addTranslation("itemGroup.skipstones.item_group", "Skipping Stones");
		return provider;
	}
}
