package xx.expand.abilityList;

import arc.Core;
import arc.scene.ui.layout.Table;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.ui.Bar;
import xx.expand.CompositeUnitEntity;
import xx.expand.Palxx;

public class ReviveLifeAbilityList extends Ability {
    public int revivesSum ;
    public int revivesMax ;
    public float retime ;
    public float refrigeration;

    public ReviveLifeAbilityList(int revivesMax, float retime){
        this.revivesMax = revivesMax;
        this.retime = retime;
    }





    @Override
    public void update(Unit unit){
        if (unit instanceof CompositeUnitEntity reviveUnit) {
            this.revivesSum = reviveUnit.revivesSum;
            this.revivesMax = reviveUnit.revivesMax;
            this.refrigeration = reviveUnit.refrigeration;

        }

    }

    @Override
    public void addStats(Table t){
        super.addStats(t);
        t.add(Core.bundle.format("revive-desc")).row();
        t.add(abilityStat("revive",revivesMax)).row();
        t.add(abilityStat("retime",retime)).row();
    }





    //状态显示
    @Override
    public void displayBars(Unit unit, Table bars) {

        if ( revivesMax > 0) {
            bars.add(new Bar(
                    () -> "血条: " + revivesSum + "/" + revivesMax,
                    () -> revivesSum <= 1 ? Palxx.colorWarn : revivesSum<=revivesMax/2 ? Palxx.colorRemind : Palxx.colorRevive,
                    () -> (float) revivesSum / revivesMax
            )).row();
            bars.add(new Bar(
                    () -> "冷却: " + refrigeration+ "/" + retime,
                    () -> Palxx.colorRevive,
                    () -> refrigeration / retime
            )).row();
        }
    }







}


