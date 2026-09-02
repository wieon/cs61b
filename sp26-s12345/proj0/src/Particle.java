import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
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
        lifespan = -1;
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
            return new Color(0, 255, 0);
        } else if (flavor == ParticleFlavor.FIRE) {
            return new Color(255, 0, 0);
        } else if (flavor == ParticleFlavor.FLOWER) {
            return new Color(255, 141, 161);
        }
        return Color.GRAY;
    }

    public void moveInto(Particle other) {
        other.flavor = this.flavor;
        other.lifespan = this.lifespan;

        this.flavor = ParticleFlavor.EMPTY;
        this.lifespan = -1;
    }

    public void fall(Map<Direction, Particle> neighbors) {
        if (neighbors.containsKey(Direction.DOWN)) {
            if (neighbors.get(Direction.DOWN).flavor == ParticleFlavor.EMPTY) {
                moveInto(neighbors.get(Direction.DOWN));
            }
        }
    }

    public void flow(Map<Direction, Particle> neighbors) {
        int n = StdRandom.uniformInt(3);
        if (n == 0) {
            return;
        } else if (n == 1) {
            if (neighbors.get(Direction.LEFT).flavor == ParticleFlavor.EMPTY) {
                this.moveInto(neighbors.get(Direction.LEFT));
            }
        } else if (n == 2) {
            if (neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.EMPTY) {
                this.moveInto(neighbors.get(Direction.RIGHT));
            }
        }
    }


    public void grow(Map<Direction, Particle> neighbors) {
    }

    public void burn(Map<Direction, Particle> neighbors) {
    }

    public void action(Map<Direction, Particle> neighbors) {
        if (this.flavor == ParticleFlavor.EMPTY) {
            return;
        }
        if (this.flavor != ParticleFlavor.BARRIER) {
            fall(neighbors);
        }
        if (this.flavor == ParticleFlavor.WATER) {
            flow(neighbors);
        }
    }
}