package net.tazgirl.tutilz.events.event_priority_overrides.LivingDamageEvent;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.tazgirl.tutilz.events.event_priority_overrides.EventAccessor;

public class LivingDamageEventAccessor<T extends LivingDamageEvent> extends EventAccessor<LivingDamageEvent>
{

    public LivingDamageEventAccessor(LivingDamageEvent mySource, float baseDamage)
    {
        super(mySource);

        this.finalDamage = baseDamage;
        this.baseDamage = baseDamage;
    }


    // finalDamage = the damage that will be done will all priority sorted events have been called
    private float finalDamage;

    // baseDamage = the damage that was originally sent before any listeners received the event
    private float baseDamage;

    // baseDamageModifier = a modifier used for any upgrades that want to apply modifications to the base damage while preserving the original amount.
    private float baseDamageModifier = 0;

    // i.e if your event increases final damage by 5% of base damage, but you wanted that to synergise with an upgrade that grants +1 base damage
    // then you would do `eventAccesor.AddFinalDamage(eventAccesor.GetModifiedBaseDamage * 0.05)` while making sure you have a high priority number
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


    public static class Pre extends LivingDamageEventAccessor<LivingDamageEvent.Pre>
    {
        LivingDamageEvent.Pre pre;

        public Pre(LivingDamageEvent.Pre mySource, float baseDamage)
        {
            super(mySource, baseDamage);
            pre = mySource;
        }

        public LivingEntity getVictim()
        {
            return pre.getEntity();
        }

        public DamageSource getSource()
        {
            return pre.getSource();
        }

        public float getReduction(DamageContainer.Reduction reduction)
        {
            return pre.getContainer().getReduction(reduction);
        }
    }

    public static class Post extends LivingDamageEventAccessor<LivingDamageEvent.Post>
    {
        LivingDamageEvent.Post post;

        public Post(LivingDamageEvent.Post mySource, float baseDamage)
        {
            super(mySource, baseDamage);
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
