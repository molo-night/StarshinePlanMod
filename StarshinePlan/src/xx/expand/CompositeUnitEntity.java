package xx.expand;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.input.KeyCode;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.Fx;
import mindustry.gen.Player;
import mindustry.gen.UnitEntity;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;

import static mindustry.Vars.player;

public class CompositeUnitEntity extends UnitEntity{


    public int
            increaseCleanIntervals,
            cleanIntervalsMax,
            cleanIntervals ,
            revivesMax,
            revivesSum;
    public float
            damageHeal,
            damageHealMultiply,
            chance,
            retime,
            refrigeration = 0f;
    private boolean
            banConvey = true;

    //注册，必不可缺
    @Override
    public int classId() {
        return EntityRegister.getID(CompositeUnitEntity.class);
    }


    @Override
    public void setType(UnitType type) {
        super.setType(type);
        if (type instanceof CompositeUnitType CompositeType) {
            this.revivesMax = this.revivesSum = Math.max(CompositeType.revivesMax, 0);
            this.retime = CompositeType.retime;
            this.damageHeal = CompositeType.damageHeal;
            this.damageHealMultiply = CompositeType.damageHealMultiply;
            this.cleanIntervalsMax = CompositeType.cleanIntervalsMax;
            this.increaseCleanIntervals = this.cleanIntervals = Math.max(CompositeType.increaseCleanIntervals, 0);
        }
    }
    @Override
    public float mass() {
        return 8000000;
    }




    @Override
    public void update(){
        super.update();
        if(revivesSum<revivesMax) {
            refrigeration += Time.delta/Core.graphics.getFramesPerSecond();//不知道为什么，Time.delta在60帧下恒为1
            if(refrigeration>=retime){
                revivesSum++;
                refrigeration = 0f;
            }
        }





        if (Core.input.keyTap(KeyCode.k) && banConvey && controller() instanceof Player && player.unit() == this ) teleportToMouse();

    }



    private void teleportToMouse() {
        banConvey = false;

        float oldX = x , oldY = y;
        float mouseX = Core.input.mouseWorldX();
        float mouseY = Core.input.mouseWorldY();
        Fxxx.fxConveyCircle.at(mouseX, mouseY);

        Time.run(5f, () -> {
            if (Core.scene.hasDialog() || Core.scene.hasField() || player == null || player.dead()) {
                banConvey = true;
                return;
            }
            Fxxx.fxConveyFillCircle.at(oldX,oldY);
            set(mouseX, mouseY);
            vel.setZero();
            Fx.unitControl.at(mouseX, mouseY,0,this);
            Fxxx.fxConveyCircleB.at(mouseX, mouseY);
            banConvey = true;
        });
    }

    @Override
    public void draw(){
        super.draw();

            Draw.z(Layer.effect);

            float radius = hitSize * 1.2f;

            Draw.alpha(0.4f);
            Draw.color(Pal.accent);
            Lines.stroke(3f);
            Lines.circle(x, y, radius);

            Draw.reset();

    }











    //伤害it's important
    @Override
    public void rawDamage(float amount1){
        float amount = Math.min(amount1, ((float) 24000 /(revivesMax-revivesSum+1)) * (float) Math.log(amount1+1));


        //受击回复
        if (Mathf.chance(chance)) {
            this.health+=damageHeal*damageHealMultiply;
        }else this.health+=damageHeal;
        if(health>maxHealth) {
            shield+= health - maxHealth;
            health = maxHealth;
        }
        if(amount < 0.1 ) {
            if(cleanIntervals < cleanIntervalsMax) cleanIntervals++;
            return;
        }


        if( cleanIntervals > 0){
            cleanIntervals--;
            return;
        }else cleanIntervals+=increaseCleanIntervals;


        boolean hadShields = this.shield > 1.0E-4F;
        if (Float.isNaN(this.health)) {
            this.health = 0.0F;
        }

        if (hadShields) {
            this.shieldAlpha = 1.0F;
        }

        float shieldDamage = Math.min(Math.max(this.shield, 0.0F), amount);
        this.shield -= shieldDamage;
        this.hitTime = 1.0F;
        amount -= shieldDamage;
        if (amount > 0.0F && this.type.killable) {
            this.health -= amount;
            if (this.health <= 0.0F && !this.dead ) {

                if(revivesSum > 0){
                    this.health = maxHealth;
                    revivesSum--;
                    if (!isAdded()) add();
                }//多重血条
                else this.kill();

            }

            if (hadShields && this.shield <= 1.0E-4F) {
                Fx.unitShieldBreak.at(this.x, this.y, 0.0F, this.type.shieldColor(this), this);
            }
        }
    }






    //数据存取
    @Override
    public void write(Writes write) {
        super.write(write);
        write.s(4);
        write.i(revivesMax);
        write.i(revivesSum);
        write.f(retime);
        write.f(refrigeration);
        write.bool(banConvey);
        write.i(cleanIntervalsMax);
        write.i(cleanIntervals);

    }
    @Override
    public void read(Reads read) {
        super.read(read);
        short REV = read.s();//版本控制
        if (REV == 3){
            revivesMax = read.i();
            revivesSum = read.i();
            retime = read.f();
            refrigeration = read.f();
            banConvey = read.bool();
        }
        if(REV == 4){
            revivesMax = read.i();
            revivesSum = read.i();
            retime = read.f();
            refrigeration = read.f();
            banConvey = read.bool();
            cleanIntervalsMax = read.i();
            cleanIntervals = read.i();
        }
    }





}