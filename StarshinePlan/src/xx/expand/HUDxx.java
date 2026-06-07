package xx.expand;

import arc.Core;
import arc.graphics.Color;
import arc.scene.ui.layout.Table;
import mindustry.gen.Unit;
import mindustry.ui.Bar;
import mindustry.ui.Styles;

import static mindustry.Vars.player;

public class HUDxx {

    private final Table container;

    public HUDxx() {
        container = new Table();
        container.top().left();
        container.setFillParent(true);

        // 设置内边距，避免被系统UI遮挡
        //container.marginLeft(10);
        //container.marginTop(10);
    }







    public void build() {
        container.table(Styles.none, panel -> {
            // 进度条
            panel.add(new SideBarxx(
                    () -> player.unit() instanceof CompositeUnitEntity revive ?
                            (float) revive.revivesSum / revive.revivesMax : 0f,
                    () -> player.unit() instanceof CompositeUnitEntity revive && revive.revivesSum == 1,
                    true,
                    Palxx.colorRevive00,
                    Color.valueOf("00000000")

            )).width(40f).growY().padRight(-17);



        }).size(1, 80).padRight(4);



        Core.scene.add(container);


    }
    public void update() {
        Unit unit = player.unit();
        container.visible = unit instanceof CompositeUnitEntity;
//        if (hasRevive) container.toFront();
    }




}