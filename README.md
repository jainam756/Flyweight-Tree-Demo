
# 🌳 Flyweight Pattern - Tree Planting Optimization System

## 📌 Overview
This Java project demonstrates the **Flyweight Design Pattern** using a tree plantation simulation. It optimizes memory usage by sharing intrinsic properties (like tree type and color) across many tree instances while maintaining extrinsic data (like coordinates) individually.

---

## 🎯 Problem Statement
In a forest, thousands of trees are planted. Each tree has a type (e.g., Neem, Oak), color, and coordinates `(x, y)`. 
Creating separate full objects for each tree type leads to high memory consumption. 
We use the **Flyweight Pattern** to store shared (intrinsic) properties only once per tree type and reduce memory overhead.

---

## 🧱 Features
- Flyweight implementation using `TreeFlyweight` for shared data
- Random tree planting simulation
- Density analysis of tree types in the forest
- Left-area (x ≤ 200) density comparison
- Memory savings estimation

---

## 🧠 Design Pattern: Flyweight
- **Intrinsic State**: Tree type, color — stored in `TreeFlyweight`
- **Extrinsic State**: Coordinates `(x, y)` — stored in `Tree`
- **Factory**: `TreeFactory` caches and reuses flyweight objects

---

## 🧪 Sample Output
```
Flyweight created: Type=Neem-Tree
Flyweight created: Type=Oak-Tree
Enter number of trees to plant: 5
Planted Neem-Tree at (382, 181)
Planted Oak-Tree at (127, 63)
Planted Neem-Tree at (245, 150)
Planted Oak-Tree at (158, 87)
Planted Neem-Tree at (315, 21)
More Neem Trees: 3
Left area is more dense with Oak Trees
Estimated memory saved: 40 bytes
```

---

## 📦 How to Run
1. Clone this repo or copy the code into your Java IDE.
2. Compile and run the `FlyweightTreeDemo` class.
3. Enter the number of trees to plant when prompted.

```bash
javac FlyweightTreeDemo.java
java FlyweightTreeDemo
```

---

## 📈 Memory Comparison (Estimation)
| Without Flyweight | With Flyweight | Memory Saved |
|-------------------|----------------|---------------|
| `count * 24` bytes | `(count * 8) + (2 * 16)` bytes | Significant, especially for large `count` |

> Example: With 1000 trees, savings = 24,000 - (8,000 + 32) = ~15,968 bytes saved

---

## ✅ Benefits
- Realistic demonstration of Flyweight in a spatial simulation
- Simple yet effective for interviews and portfolios
- Teaches memory efficiency with object reuse

---
