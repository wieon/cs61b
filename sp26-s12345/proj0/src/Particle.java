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
        if (this.flavor == ParticleFlavor.PLANT ||
            this.flavor == ParticleFlavor.FLOWER ||
            this.flavor == ParticleFlavor.FIRE) {
            this.lifespan = LIFESPANS.get(this.flavor);
        } else {
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
        }
        if (flavor == ParticleFlavor.FLOWER) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FLOWER_LIFESPAN)) / FLOWER_LIFESPAN;
            int r = 120 + (int) Math.round((255 - 120) * ratio);
            int g = 70 + (int) Math.round((141 - 70) * ratio);
            int b = 80 + (int) Math.round((161 - 80) * ratio);
            return new Color(r, g, b);
        }
        if (flavor == ParticleFlavor.PLANT) {
            double ratio = (double) Math.max(0, Math.min(lifespan, PLANT_LIFESPAN)) / PLANT_LIFESPAN;
            int g = 120 + (int) Math.round((255 - 120) * ratio);
            return new Color(0, g, 0);
        }
        if (flavor == ParticleFlavor.FIRE) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FIRE_LIFESPAN)) / FIRE_LIFESPAN;
            int r = (int) Math.round(255 * ratio);
            return new Color(r, 0, 0);
        }
//        else if (flavor == ParticleFlavor.PLANT) {
//            return new Color(0, 255, 0);
//        } else if (flavor == ParticleFlavor.FIRE) {
//            return new Color(255, 0, 0);
//        } else if (flavor == ParticleFlavor.FLOWER) {
//            return new Color(255, 141, 161);
//        }
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
        int n = StdRandom.uniformInt(10);
        Map<Integer, Direction> choice = Map.of(0, Direction.UP,
                                                1, Direction.LEFT,
                                                2, Direction.RIGHT);
        if((n == 0) || (n == 1) || (n == 2)) {
            Particle newParticle = neighbors.get(choice.get(n));
            if (newParticle.flavor == ParticleFlavor.EMPTY) {
                newParticle.flavor = this.flavor;
                newParticle.lifespan = LIFESPANS.get(newParticle.flavor);
            }
        }

//        if (n == 0) {
//            if (neighbors.get(Direction.UP).flavor == ParticleFlavor.EMPTY) {
//                neighbors.get(Direction.UP).flavor = this.flavor;
//                neighbors.get(Direction.UP).lifespan = LIFESPANS.get(neighbors.get(Direction.UP).flavor);
//            }
//        } else if (n == 1) {
//            if (neighbors.get(Direction.LEFT).flavor == ParticleFlavor.EMPTY) {
//                neighbors.get(Direction.LEFT).flavor = this.flavor;
//                neighbors.get(Direction.LEFT).lifespan = LIFESPANS.get(neighbors.get(Direction.LEFT).flavor);
//            }
//        } else if (n == 2) {
//            if (neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.EMPTY) {
//                neighbors.get(Direction.RIGHT).flavor = this.flavor;
//                neighbors.get(Direction.RIGHT).lifespan = LIFESPANS.get(neighbors.get(Direction.RIGHT).flavor);
//            }
//        } else if (n >=3 && n <= 9) {
//            return;
//        }
    }

    public void burn(Map<Direction, Particle> neighbors) {
        for (Map.Entry<Direction, Particle> entry: neighbors.entrySet()) {
            Direction direction = entry.getKey();
            Particle particle = entry.getValue();
            if (particle.flavor == ParticleFlavor.PLANT ||
                particle.flavor == ParticleFlavor.FLOWER) {
                int n = StdRandom.uniformInt(10);
                if (n == 0 || n == 1 || n == 2 || n == 3) {
                    particle.flavor = ParticleFlavor.FIRE;
                    particle.lifespan = LIFESPANS.get(particle.flavor);
                }
            }
        }
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
        if (this.flavor == ParticleFlavor.PLANT || this.flavor == ParticleFlavor.FLOWER) {
            grow(neighbors);
        }
        if (this.flavor == ParticleFlavor.FIRE) {
            burn(neighbors);
        }
    }

    public void decrementLifespan() {
        if (this.lifespan > 0) {
            this.lifespan--;
        }
        if (this.lifespan == 0) {
            this.flavor = ParticleFlavor.EMPTY;
            this.lifespan = -1;
        }
    }
}

