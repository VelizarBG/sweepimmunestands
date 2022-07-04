package velizarbg.sweepimmunestands.mixins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStandEntity.class)
public class ArmorStandEntityMixin {
	@Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getTime()J"), cancellable = true)
	private void sweepNoDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		if (source.getAttacker() instanceof PlayerEntity player && EnchantmentHelper.getSweepingMultiplier(player) > 0)
			cir.setReturnValue(true);
	}
}
