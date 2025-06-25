// Flyweight Pattern: Tree Planting Optimization System
// Author: Jainam Parekh

import java.util.*;

// Flyweight class (intrinsic state)
class TreeFlyweight {
    private final String type;
    private final String color;

    public TreeFlyweight(String type, String color) {
        System.out.println("Flyweight created: Type=" + type);
        this.type = type;
        this.color = color;
    }

    public String getType() {
        return type;
    }
}

// Context class (extrinsic state)
class Tree {
    private final TreeFlyweight flyweight;
    private int x;
    private int y;

    public Tree(TreeFlyweight flyweight) {
        this.flyweight = flyweight;
    }

    public void plant(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Planted " + flyweight.getType() + " at (" + x + ", " + y + ")");
    }

    public TreeFlyweight getFlyweight() {
        return flyweight;
    }

    public int getX() {
        return x;
    }
}

// Factory to manage flyweights
class TreeFactory {
    private final Map<String, TreeFlyweight> cache = new HashMap<>();

    public TreeFlyweight getTree(String type) {
        if (!cache.containsKey(type)) {
            switch (type.toLowerCase()) {
                case "neem-tree":
                    cache.put(type, new TreeFlyweight("Neem-Tree", "Green"));
                    break;
                case "oak-tree":
                    cache.put(type, new TreeFlyweight("Oak-Tree", "Orange"));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid tree type: " + type);
            }
        }
        return cache.get(type);
    }
}

// Forest class to analyze tree distribution
class Forest {
    private final List<Tree> trees;

    public Forest(List<Tree> trees) {
        this.trees = trees;
    }

    public void analyzeDensity() {
        long neemCount = trees.stream().filter(t -> t.getFlyweight().getType().equals("Neem-Tree")).count();
        long oakCount = trees.size() - neemCount;

        if (neemCount > oakCount) {
            System.out.println("More Neem Trees: " + neemCount);
        } else if (oakCount > neemCount) {
            System.out.println("More Oak Trees: " + oakCount);
        } else {
            System.out.println("Equal number of Neem and Oak trees: " + neemCount);
        }
    }

    public void analyzeLeftSideDensity() {
        long leftNeem = trees.stream()
            .filter(t -> t.getX() <= 200 && t.getFlyweight().getType().equals("Neem-Tree")).count();
        long leftOak = trees.stream()
            .filter(t -> t.getX() <= 200 && t.getFlyweight().getType().equals("Oak-Tree")).count();

        if (leftNeem > leftOak) {
            System.out.println("Left area is more dense with Neem Trees");
        } else if (leftOak > leftNeem) {
            System.out.println("Left area is more dense with Oak Trees");
        } else {
            System.out.println("Left area is equally dense");
        }
    }
}

public class FlyweightTreeDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeFactory factory = new TreeFactory();
        List<Tree> plantedTrees = new ArrayList<>();

        TreeFlyweight neem = factory.getTree("neem-tree");
        TreeFlyweight oak = factory.getTree("oak-tree");

        System.out.print("Enter number of trees to plant: ");
        int count = sc.nextInt();

        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * 401); // 0 to 400
            int y = (int) (Math.random() * 201); // 0 to 200
            TreeFlyweight tfw = (Math.random() < 0.5) ? neem : oak;
            Tree t = new Tree(tfw);
            t.plant(x, y);
            plantedTrees.add(t);
        }

        Forest forest = new Forest(plantedTrees);
        forest.analyzeDensity();
        forest.analyzeLeftSideDensity();

        // Memory estimation (simplified)
        int originalMemory = count * 24; // without Flyweight (intrinsic in every object)
        int optimizedMemory = (count * 8) + (2 * 16); // shared flyweights
        int saved = originalMemory - optimizedMemory;
        System.out.println("Estimated memory saved: " + saved + " bytes");
    }
}
