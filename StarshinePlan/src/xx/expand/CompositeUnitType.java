package xx.expand;

import mindustry.type.UnitType;
import xx.expand.abilityList.DamageHealAbilityList;
import xx.expand.abilityList.ReviveLifeAbilityList;

public class CompositeUnitType extends UnitType {

    public int
            revivesMax = 0,
            increaseCleanIntervals = 0,
            cleanIntervalsMax = 0;
    public float
            damageHeal = 0,
            damageHealMultiply = 1f,
            chance = 0f,
            retime = 40f;





/*全部预定好了，对于ui方面，我没招了*/
    public CompositeUnitType(String name) {
        super(name);
        constructor = CompositeUnitEntity::new;
    }
    public void List(){
        if(revivesMax>0) abilities.add(new ReviveLifeAbilityList(revivesMax,retime));
        if(damageHeal>0) abilities.add(new DamageHealAbilityList(damageHeal,damageHealMultiply,chance));
    }




/*    信息统计
    @Override
    public void setStats() {
        super.setStats();
        Stat revivesStat = new Stat("revives", StatCat.general);
        stats.add(revivesStat, table -> {
            table.row();
            table.table(back -> {
                back.background(Styles.grayPanel);  // 灰色背景
                back.defaults().left().pad(4);
                back.row();
                back.left();
                back.add("                       [accent] 多重血条 []");;
                back.row();
                back.add("专门为血牛设计，抵消最后一次伤害");
                back.row();
                back.add("[accent]" + revivesSum + " [accent]层" +" [gray]血条[]"  );
                back.row();//文本
            }).growX().pad(4);
        });
    }*/










}



