

import java.util.*;
import java.util.concurrent.*;

public class RoomStatusStore {
    private static final Map<Integer, Boolean> status = new ConcurrentHashMap<>();

    static {
        for (int i = 1; i <= 10; i++) {
            status.put(i, true); // All rooms initially available
        }

        // Randomly toggle room availability every 10 seconds
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            int room = new Random().nextInt(10) + 1;
            status.put(room, !status.get(room)); // Toggle status
        }, 5, 10, TimeUnit.SECONDS);
    }

    public static Map<Integer, Boolean> getStatus() {
        return new HashMap<>(status);
    }
}
