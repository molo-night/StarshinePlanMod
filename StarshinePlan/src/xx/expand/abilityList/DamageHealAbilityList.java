package xx.expand.abilityList;

import arc.scene.ui.layout.Table;
import mindustry.entities.abilities.Ability;

public class DamageHealAbilityList extends Ability {
    public float damageHeal,damageHealMultiply,chance;

    public DamageHealAbilityList(float damageHeal, float damageHealMultiply,float chance){
        this.damageHeal = damageHeal;
        this.damageHealMultiply = damageHealMultiply;
        this.chance = chance;
    }

    @Override
    public void addStats(Table t){
        super.addStats(t);
        t.add(abilityStat("damageheal-desc")).row();
        t.add(abilityStat("damageheal",damageHeal,damageHealMultiply,chance*100)).row();
    }




}
