public class Car {

    String model;
    int wheels;

    public Car(String m) {
        this.model = m;  // car type
        this.wheels = 4;
    }

    public void drive() {
        if (this.wheels < 4) {
            System.out.println(this.model + " no go vroom");
            return;
        }
        System.out.println(this.model + " go vroom");
    }

    public int getNumWheels() {
        return this.wheels;
    }

    public void driveIntoDitch(int wheelsLost) {
        this.wheels = this.wheels - wheelsLost;
    }

    public static void main(String[] args) {
        Car c1;  // declare type
        Car c2;

        c1 = new Car("Civic Type R");  // create "new" object
        c2 = new Car("Toypta Camry");

        c1.drive();
        c1.driveIntoDitch(2);
        c1.drive();

        System.out.println(c2.getNumWheels());
    }
}