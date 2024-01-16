package loqor.skipstones.core.items;

import loqor.skipstones.core.SkipEntityTypes;
import loqor.skipstones.core.entities.SkippingStoneEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkippingStoneItem extends Item {
    public SkippingStoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        if (!world.isClient) {
            SkippingStoneEntity skippingStoneEntity = new SkippingStoneEntity(SkipEntityTypes.SKIPPING_STONE_ENTITY_TYPE, world, user);
            skippingStoneEntity.setItem(itemStack);
            skippingStoneEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f, 1.0f);
            world.spawnEntity(skippingStoneEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Stone Type:").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("> Stone").formatted(Formatting.BLUE));
    }
}
