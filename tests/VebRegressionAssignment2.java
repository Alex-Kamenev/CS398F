package com.akamenev.assignment2;

import java.util.Objects;
import java.util.Random;
import java.util.TreeMap;

public class VebRegressionAssignment2 {
    public static void main(String[] args) {
        VEBQueue<String> queue = new VEBQueue<>(64);
        TreeMap<Integer, String> expected = new TreeMap<>();
        Random random = new Random(42);
        for (int operation = 0; operation < 500; operation++) {
            int key = random.nextInt(64);
            if (operation % 3 == 0 && !expected.containsKey(key)) {
                queue.vEBInsert(queue, key, "value-" + key);
                expected.put(key, "value-" + key);
            } else if (operation % 3 == 1) {
                queue.vEBDelete(queue, key);
                expected.remove(key);
            } else {
                int newKey = random.nextInt(64);
                queue.changePriority(queue, key, newKey);
                if (expected.containsKey(key) && !expected.containsKey(newKey)) expected.put(newKey, expected.remove(key));
            }
            if (queue.vEBmin(queue) != (expected.isEmpty() ? -1 : expected.firstKey())) throw new AssertionError("Minimum");
            if (queue.vEBmax(queue) != (expected.isEmpty() ? -1 : expected.lastKey())) throw new AssertionError("Maximum");
            for (int probe = -1; probe <= 64; probe++) {
                if (queue.vEBMember(queue, probe) != expected.containsKey(probe)) throw new AssertionError("Membership");
                if (!Objects.equals(queue.getData(queue, probe), expected.get(probe))) throw new AssertionError("Payload");
                Integer higher = expected.higherKey(probe);
                Integer lower = expected.lowerKey(probe);
                if (queue.vEBSuccessor(queue, probe) != (higher == null ? -1 : higher)) throw new AssertionError("Successor");
                if (queue.vEBPredecessor(queue, probe) != (lower == null ? -1 : lower)) throw new AssertionError("Predecessor");
            }
        }
        while (!expected.isEmpty()) {
            String value = expected.pollFirstEntry().getValue();
            if (!Objects.equals(queue.extractMin(queue), value)) throw new AssertionError("Extraction");
        }
        try { queue.extractMin(queue); throw new AssertionError("Empty extraction"); }
        catch (java.util.NoSuchElementException expectedError) { }
        try { new VEBQueue<>(3); throw new AssertionError("Invalid universe"); }
        catch (IllegalArgumentException expectedError) { }
        System.out.println("vEB regressions passed: " + VEBQueue.class.getName());
    }
}
