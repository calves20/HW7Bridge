import java.util.ArrayList;
//Author @Curtis Alves
public class OneLaneBridge extends Bridge {
    private final int bridgeLimit;

    public OneLaneBridge(int limit) {
        super();
        this.bridgeLimit = limit;
    }

    @Override
    public synchronized void arrive(Car car) throws InterruptedException {
        // Wait if bridge is full or if the car's direction doesn't match the bridge's direction
        while (bridge.size() >= bridgeLimit || (!bridge.isEmpty() && car.getDirection() != direction)) {
            wait();
        }
        // Set bridge direction based on the first car's direction
        direction = car.getDirection();
        car.setEntryTime(currentTime++);
        bridge.add(car);
        System.out.println("Bridge (dir=" + direction + "): " + bridge);
    }

    @Override
    public synchronized void exit(Car car) {
        bridge.remove(car);
        System.out.println("Bridge (dir=" + direction + "): " + bridge);
        if (bridge.isEmpty()) {
            // Notify all waiting threads when the bridge becomes empty
            notifyAll();
        }
    }
}
