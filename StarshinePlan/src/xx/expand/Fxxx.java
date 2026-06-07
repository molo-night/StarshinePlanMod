package xx.expand;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import mindustry.entities.Effect;
import mindustry.graphics.Layer;

import javax.sound.sampled.Line;


public class Fxxx {


    public static final Effect fxConveyCircle = new Effect(25f, e -> {

        float progress = e.fout(Interp.pow2Out);

        float radius = progress * 300f;

        float alpha = e.fin(Interp.pow2In);

        Draw.z(Layer.effect);
        Draw.alpha(alpha);
        Draw.color(Color.white);


        Lines.stroke(3f * alpha);
        Lines.square(e.x, e.y, radius,45);



        Draw.reset();


        float progress2 = e.fout();

        float radius2 = progress2 * 300f;

        float alpha2 = e.fin();


        Draw.z(Layer.effect);
        Draw.alpha(alpha2);
        Draw.color(Color.white);

        Lines.stroke(3f * alpha2);
        Lines.square(e.x, e.y, radius2,45);


        Draw.reset();
    }),
            //替补
    fx= new Effect(0f, e -> {
        Draw.reset();
    }),


    fxConveyFillCircle= new Effect(150f, e -> {

        float progress = e.fout(Interp.pow2In);

        float radius = progress * 30f;

        Draw.z(Layer.effect);
        Draw.alpha(1);
        Draw.color(Color.white);

        Lines.stroke(1f);
        Fill.circle(e.x, e.y, radius);




        Draw.reset();
    }),






    fxConveyCircleB= new Effect(60f, e -> {

        float progress = e.fin(Interp.pow2Out);

        float radius = progress * 400f;

        float alpha = e.fout(Interp.pow2In);

        Draw.z(Layer.effect);
        Draw.alpha(alpha);
        Draw.color(Color.white);

        Lines.stroke(1.4f * alpha);
        Lines.square(e.x, e.y, radius,45);


        Draw.reset();
    });




}
