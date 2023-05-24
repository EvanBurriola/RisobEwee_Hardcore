package risobewee_hardcore.events.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import risobewee_hardcore.RisobEwee_HardcoreMain;

import javax.annotation.Nonnull;
import java.util.List;

public class EvokerLootModifier extends LootModifier {
    private final Item addition;

    protected EvokerLootModifier(LootItemCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        //Removes totem of undying.
        RisobEwee_HardcoreMain.LOGGER.info("doApply");
        generatedLoot.clear();
        generatedLoot.add(new ItemStack(addition, 1));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<EvokerLootModifier> {

        @Override
        public EvokerLootModifier read(ResourceLocation name, JsonObject object,
                                                        LootItemCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object, "addition")));
            return new EvokerLootModifier(conditionsIn, addition);
        }

        @Override
        public JsonObject write(EvokerLootModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            return json;
        }
    }
}
