package net.tazgirl.tutilz.events.priority_events.vanilla_overrides.LivingDamageEvent;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.tazgirl.tutilz.events.priority_events.EventAccessor;

public abstract class LivingDamageOverrideAccessor extends EventAccessor<LivingDamageOverrideAccessor>
{

    public LivingDamageOverrideAccessor(LivingDamageEvent mySource, float baseDamage, float currentFinalDamage)
    {
        super(mySource);

        this.finalDamage = currentFinalDamage;
        this.baseDamage = baseDamage;
    }


    // finalDamage = the damage that will be done once all priority sorted events have been called
    protected float finalDamage;

    // baseDamage = the damage that was originally sent before any listeners received the event
    protected float baseDamage;

    // baseDamageModifier = a modifier used for any upgrades that want to apply modifications to the base damage while preserving the original amount.
    protected float baseDamageModifier = 0;

    // i.e if your event increases final damage by 5% of base damage, but you wanted that to synergise with an upgrade that grants +1 base damage
    // then you would do `eventAccessor.addFinalDamage(eventAccessor.getModifiedBaseDamage * 0.05)` while making sure you have a high priority number
    // so it occurs after any base damage modifiers


    public float getCurrentFinalDamage()
    {
        return finalDamage;
    }

    public float getBaseDamage()
    {
        return baseDamage;
    }

    public float getModifiedBaseDamage()
    {
        return baseDamage + baseDamageModifier;
    }

    public float getBaseDamageModifier()
    {
        return baseDamageModifier;
    }


    public static class Pre extends LivingDamageOverrideAccessor
    {
        LivingDamageEvent.Pre pre;

        public Pre(LivingDamageEvent.Pre mySource, float baseDamage, float currentFinalDamage)
        {
            super(mySource, baseDamage, currentFinalDamage);
            pre = mySource;
        }

        public LivingEntity getVictim()
        {
            return pre.getEntity();
        }

        public Entity getDirectEntity()
        {
            return pre.getSource().getDirectEntity();
        }

        public Entity getSourceEntity()
        {
            return pre.getSource().getEntity();
        }

        public DamageSource getSource()
        {
            return pre.getSource();
        }

        public float getReduction(DamageContainer.Reduction reduction)
        {
            return pre.getContainer().getReduction(reduction);
        }

        public void AddFinalDamage(float amount)
        {
            finalDamage += amount;
        }

        public void AddBaseDamageModifier(float amount)
        {
            baseDamageModifier += amount;
        }
    }

    public static class Post extends LivingDamageOverrideAccessor
    {
        LivingDamageEvent.Post post;

        public Post(LivingDamageEvent.Post mySource, float baseDamage, float currentFinalDamage)
        {
            super(mySource, baseDamage, currentFinalDamage);
            post = mySource;
        }

        // Nothing to break in Post like in Pre so just handing off the source to save time
        public LivingDamageEvent.Post getSourceEvent()
        {
            return post;
        }

        public LivingEntity getVictim()
        {
            return post.getEntity();
        }

    }
}
