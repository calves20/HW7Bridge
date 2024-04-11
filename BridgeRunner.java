//Auhtor @Curtis Alves
public class BridgeRunner {
    public static void main(String[] args) {
        // Check command line inputs
        if (args.length != 2) {
            System.out.println("Usage: java BridgeRunner <bridge limit> <num cars>");
            return;
        }

        int bridgeLimit = Integer.parseInt(args[0]);
        int numCars = Integer.parseInt(args[1]);
        if (bridgeLimit <= 0 || numCars <= 0) {
            System.out.println("Error: bridge limit and/or num cars must be positive.");
            return;
        }

        // Instantiate the bridge
        OneLaneBridge bridge = new OneLaneBridge(bridgeLimit);

        // Allocate space for threads and start them
        Thread[] carThreads = new Thread[numCars];
        for (int i = 0; i < numCars; i++) {
            Car car = new Car(i, bridge);
            carThreads[i] = new Thread(car);
            carThreads[i].start();
        }

        // Join the threads
        for (int i = 0; i < numCars; i++) {
            try {
                carThreads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        System.out.println("All cars have crossed!!");
    }
}
