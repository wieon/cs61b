import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Particle {
    public ParticleFlavor flavor;
    public int lifespan;

    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;
    public static final Map<ParticleFlavor, Integer> LIFESPANS =
            Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
                   ParticleFlavor.PLANT, PLANT_LIFESPAN,
                   ParticleFlavor.FIRE, FIRE_LIFESPAN);

    public Particle(ParticleFlavor flavor) {
        this.flavor = flavor;
        if (this.flavor == ParticleFlavor.FLOWER || this.flavor == ParticleFlavor.PLANT || this.flavor == ParticleFlavor.FIRE){
            this.lifespan = LIFESPANS.get(this.flavor);
        }
        else {
            this.lifespan = -1;
        }
    }

    public Color color() {
        if (flavor == ParticleFlavor.EMPTY) {
            return Color.BLACK;
        } else if (flavor == ParticleFlavor.SAND) {
            return Color.YELLOW;
        } else if (flavor == ParticleFlavor.BARRIER) {
            return Color.GRAY;
        } else if (flavor == ParticleFlavor.WATER) {
            return Color.BLUE;
        } else if (flavor == ParticleFlavor.FOUNTAIN) {
            return Color.CYAN;
        } else if (flavor == ParticleFlavor.PLANT) {
            double ratio = (double) Math.max(0, Math.min(lifespan, PLANT_LIFESPAN)) / PLANT_LIFESPAN;
            int g = 120 + (int) Math.round((255 - 120) * ratio);
            return new Color(0, g, 0);
        } else if (flavor == ParticleFlavor.FIRE) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FIRE_LIFESPAN)) / FIRE_LIFESPAN;
            int r = (int) Math.round(255 * ratio);
            return new Color(r, 0, 0);
        } else if (flavor == ParticleFlavor.FLOWER) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FLOWER_LIFESPAN)) / FLOWER_LIFESPAN;
            int r = 120 + (int) Math.round((255 - 120) * ratio);
            int g = 70 + (int) Math.round((141 - 70) * ratio);
            int b = 80 + (int) Math.round((161 - 80) * ratio);
            return new Color(r, g, b);
        }
        return new Color(255, 255, 255);
    }

    public void moveInto(Particle other) {
        other.flavor = this.flavor;
        other.lifespan = this.lifespan;

        this.flavor = ParticleFlavor.EMPTY;
        this.lifespan = -1;
    }

    public void fall(Map<Direction, Particle> neighbors) {
        if (neighbors.containsKey(Direction.DOWN)){
            if (neighbors.get(Direction.DOWN).flavor == ParticleFlavor.EMPTY){
                this.moveInto(neighbors.get(Direction.DOWN));
            }
        }
    }

    public void flow(Map<Direction, Particle> neighbors) {
        int random_choice = StdRandom.uniformInt(3);
        if (random_choice == 0){
            return;
        } else if (random_choice == 1) {
            if (neighbors.get(Direction.LEFT).flavor == ParticleFlavor.EMPTY ){
                this.moveInto(neighbors.get(Direction.LEFT));
            }

        } else if (random_choice == 2) {
            if (neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.EMPTY) {
                this.moveInto(neighbors.get(Direction.RIGHT));
            }
        }
    }

    public void grow(Map<Direction, Particle> neighbors) {
        int random_choice = StdRandom.uniformInt(10);
        Map<Integer, Direction> choices = Map.of(0, Direction.UP, 1, Direction.LEFT, 2, Direction.RIGHT);
        if ((random_choice == 0) || (random_choice == 1) || (random_choice == 2)){
            Particle upParticle = neighbors.get(choices.get(random_choice));
            if (upParticle.flavor == ParticleFlavor.EMPTY){
                upParticle.flavor = this.flavor;
                upParticle.lifespan = LIFESPANS.get(upParticle.flavor);
            }
        }
//        else if (random_choice == 1){
//            Particle upParticle = neighbors.get(Direction.LEFT);
//            if (upParticle.flavor == ParticleFlavor.EMPTY){
//                upParticle.flavor = this.flavor;
//                upParticle.lifespan = LIFESPANS.get(upParticle.flavor);
//            }
//        }
//        else if (random_choice == 2){
//            Particle upParticle = neighbors.get(Direction.RIGHT);
//            if (upParticle.flavor == ParticleFlavor.EMPTY){
//                upParticle.flavor = this.flavor;
//                upParticle.lifespan = LIFESPANS.get(upParticle.flavor);
//            }
//        }

    }

    public void burn(Map<Direction, Particle> neighbors) {
//        int random_choice = StdRandom.uniformInt(10); error “独立概率（independently）”
        for (Map.Entry<Direction, Particle> entry : neighbors.entrySet()){
            Direction direction = entry.getKey();
            Particle particle = entry.getValue();
            if (particle.flavor == ParticleFlavor.FLOWER || particle.flavor == ParticleFlavor.PLANT){
                int random_choice = StdRandom.uniformInt(10);
                if ((random_choice == 0) || (random_choice == 1) || (random_choice == 2) || (random_choice == 3)){
                    particle.flavor = ParticleFlavor.FIRE;
                    particle.lifespan = LIFESPANS.get(particle.flavor);
                }
            }
        }
    }

    public void decrementLifespan(){
        if (this.lifespan > 0){
            this.lifespan -= 1;
        } if (this.lifespan == 0) {
            this.flavor = ParticleFlavor.EMPTY;
            this.lifespan = -1;
        }
    }

    public void action(Map<Direction, Particle> neighbors) {
        if (this.flavor == ParticleFlavor.EMPTY){
            return;
        }
        if (this.flavor != ParticleFlavor.BARRIER) {
            this.fall(neighbors);
        }
        if (this.flavor == ParticleFlavor.WATER) {
            this.flow(neighbors);
        }
        if (this.flavor == ParticleFlavor.FLOWER  || this.flavor == ParticleFlavor.PLANT){
            this.grow(neighbors);
        }
        if (this.flavor == ParticleFlavor.FIRE){
            this.burn(neighbors);
        }
    }
}