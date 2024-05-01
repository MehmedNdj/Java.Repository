package Projects;
import java.util.*;

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class Graph {
    private final int width;
    private final int height;
    private final Map<Point, Set<Point>> adjacencyList;

    public Graph(int width, int height) {
        this.width = width;
        this.height = height;
        this.adjacencyList = new HashMap<>();
        initializeGraph();
    }

    private void initializeGraph() {
        // Create points and add them to the adjacency list
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Point point = new Point(x, y);
                adjacencyList.put(point, new HashSet<>());
            }
        }

        // Add edges between neighboring points
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Point current = new Point(x, y);
                addEdges(current);
            }
        }
    }

    private void addEdges(Point current) {
        int x = current.getX();
        int y = current.getY();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue; // Skip the current point
                int newX = x + dx;
                int newY = y + dy;
                if (isValidPoint(newX, newY)) {
                    Point neighbor = new Point(newX, newY);
                    adjacencyList.get(current).add(neighbor);
                }
            }
        }
    }

    private boolean isValidPoint(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void addEdge(Point from, Point to) {
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from); // Assuming undirected graph
    }

    public void removePoint(Point point) {
        adjacencyList.remove(point);
        // Remove connections to the removed point
        for (Set<Point> neighbors : adjacencyList.values()) {
            neighbors.remove(point);
        }
    }

    public Set<Point> getNeighbors(Point point) {
        return adjacencyList.get(point);
    }

    public List<Point> shortestPath(Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        Map<Point, Point> parentMap = new HashMap<>();
        Set<Point> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.equals(end)) {
                return reconstructPath(parentMap, start, end);
            }

            for (Point neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        // No path found
        return Collections.emptyList();
    }

    private List<Point> reconstructPath(Map<Point, Point> parentMap, Point start, Point end) {
        List<Point> path = new ArrayList<>();
        Point current = end;
        while (!current.equals(start)) {
            path.add(current);
            current = parentMap.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}

public class RobotPathPlanning {
    public static void main(String[] args) {
        final int WIDTH = 80;
        final int HEIGHT = 25;

        // Initialize the graph
        Graph graph = new Graph(WIDTH, HEIGHT);

        // Define obstacle parameters
        Point obstacleCenter = new Point(5, 10);
        int obstacleRadius = 7;

        // Add edges to the graph and remove points inside the obstacle
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Point current = new Point(x, y);
                if (isInsideObstacle(current, obstacleCenter, obstacleRadius)) {
                    graph.removePoint(current);
                } else {
                    for (Point neighbor : graph.getNeighbors(current)) {
                        if (!isInsideObstacle(neighbor, obstacleCenter, obstacleRadius)) {
                            graph.addEdge(current, neighbor);
                        }
                    }
                }
            }
        }

        // Define points of interest
        Point[] pointsOfInterest = {
                new Point(0, 0),   // A
                new Point(0, 23),  // B
                new Point(79, 5),  // C
                new Point(20, 15)  // D
        };

        // Find shortest paths between points of interest
        for (int i = 0; i < pointsOfInterest.length; i++) {
            for (int j = i + 1; j < pointsOfInterest.length; j++) {
                Point start = pointsOfInterest[i];
                Point end = pointsOfInterest[j];
                List<Point> shortestPath = graph.shortestPath(start, end);
                System.out.println("Shortest path from " + start + " to " + end + ": " + shortestPath);
            }
        }

        // Display the result
        displayResult(graph, pointsOfInterest, obstacleCenter, obstacleRadius);
    }

    private static void displayResult(Graph graph, Point[] pointsOfInterest, Point obstacleCenter, int obstacleRadius) {
        final int CONSOLE_WIDTH = 80;
        final int CONSOLE_HEIGHT = 25;

        char[][] console = new char[CONSOLE_HEIGHT][CONSOLE_WIDTH];
        for (char[] row : console) {
            Arrays.fill(row, '.'); // Fill the console with dots representing empty space
        }

        // Mark obstacle points with asterisks
        for (int x = 0; x < CONSOLE_WIDTH; x++) {
            for (int y = 0; y < CONSOLE_HEIGHT; y++) {
                Point current = new Point(x, y);
                if (isInsideObstacle(current, obstacleCenter, obstacleRadius)) {
                    console[y][x] = '*';
                }
            }
        }

        // Mark points of interest with letters
        char label = 'A';
        for (Point poi : pointsOfInterest) {
            int x = poi.getX();
            int y = poi.getY();
            if (x >= 0 && x < CONSOLE_WIDTH && y >= 0 && y < CONSOLE_HEIGHT) {
                console[y][x] = label++;
            }
        }

        // Output the console contents
        for (char[] row : console) {
            System.out.println(row);
        }
    }

    private static boolean isInsideObstacle(Point point, Point obstacleCenter, int obstacleRadius) {
        int dx = point.getX() - obstacleCenter.getX();
        int dy = point.getY() - obstacleCenter.getY();
        return dx * dx + dy * dy <= obstacleRadius * obstacleRadius;
    }
}


