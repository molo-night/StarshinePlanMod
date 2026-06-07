package xx.content;

import mindustry.content.Fx;
import mindustry.entities.abilities.ShieldArcAbility;
import mindustry.entities.bullet.MissileBulletType;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Weapon;
import xx.expand.CompositeUnitType;


public class XXUnitTypes {

    public static CompositeUnitType froststarshine;





    public static void load(){



        froststarshine = new CompositeUnitType("frost-starshine"){{//凌城G
            health =36;
            armor = 8f;
            hitSize = 160f;
            speed = 0.101f;
            rotateSpeed = 0.08f;
            accel = 0.01f;//加速度
            drag = 0.0028f;//阻力系数
            flying = true;
            buildSpeed = 10f;
            faceTarget = false;

            revivesMax = 5 ;
            retime = 10f;
            damageHeal = 240f;
            damageHealMultiply = 2f;
            chance = 0.3f;
            cleanIntervalsMax = 10;//最高免疫次数
            increaseCleanIntervals = 1;//受到伤害后，免疫此后受到的伤害的次数。同时，这也是初始免疫次数



            List();
            abilities.add(new ShieldArcAbility(){{
                region = "tecta-shield";
                radius = 1f;//半径
                angle = 270f;//角度
                regen = 10500f / 60f;//回复
                cooldown = 0f;//冷却
                max = 2500000f;
                y = 0f;
                width = 10f;//宽度
                whenShooting = false;
                chanceDeflect = 1f;//反弹概率
            }});









            weapons.add(new Weapon("frost-starshine-weapon"){{
                shootSound = Sounds.shootMalign;
                mirror = true;
                top = false;

                x = 62/4f;
                y = 1f;
                shootY = 47 / 4f;
                recoil = 3f;
                reload = 40f;
                shake = 3f;
                cooldownTime = 40f;

                shoot.shots = 3;
                inaccuracy = 3f;
                velocityRnd = 0.33f;


                bullet = new MissileBulletType(4.2f, 51){{
                    homingPower = 0.2f;
                    weaveMag = 4;
                    weaveScale = 4;
                    lifetime = 55f;
                    shootEffect = Fx.shootBig2;
                    smokeEffect = Fx.shootSmokeTitan;
                    splashDamage = 60f;
                    splashDamageRadius = 30f;
                    hitSound = Sounds.none;
                    width = height = 10f;

                    lightColor = trailColor = backColor = Pal.techBlue;
                    lightRadius = 40f;
                    lightOpacity = 0.7f;

                    trailWidth = 2.8f;
                    trailLength = 20;
                    trailChance = -1f;
                    despawnSound = Sounds.explosionDull;

                    despawnEffect = Fx.none;
                    hitEffect = new ExplosionEffect(){{
                        lifetime = 20f;
                        waveStroke = 2f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 12f;
                        smokeSize = 0f;
                        smokeSizeBase = 0f;
                        sparks = 10;
                        sparkRad = 35f;
                        sparkLen = 4f;
                        sparkStroke = 1.5f;
                    }};
                }};
            }});




        }};


    }








}
