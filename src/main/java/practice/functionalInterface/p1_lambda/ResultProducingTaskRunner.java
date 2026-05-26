package practice.functionalInterface.p1_lambda;

import java.util.Optional;
import java.util.function.Supplier;

/*
Problem 2 — The Result-Producing Task Runner
Upgrade your runner. Create a method executeAndReturn(Supplier<T> task) that runs a task and returns its result. Wrap the execution in a try-catch — if the supplier throws any exception, catch it and return Optional.empty(). If it succeeds, return Optional.of(result).
Test it with:

A supplier that returns a string (success case)
A supplier that throws a RuntimeException (failure case)
A supplier that computes something (e.g., parses an integer from a string — try both a valid and invalid string)

 */

public class ResultProducingTaskRunner {

    // Returns Optional<T> — present on success, empty on any failure
    public static <T> Optional<T> executeAndReturn(Supplier<T> task) {
        System.out.println("[START] Running task...");
        try {
            T result = task.get();
            System.out.println("[END] Task completed.\n");
            return Optional.of(result);          // success path
        } catch (Exception e) {
            System.out.println("[FAILED] " + e.getMessage() + "\n");
            return Optional.empty();             // failure path — no null anywhere
        }
    }

    public static void main(String[] args) {

        // Test 1 — success: supplier returns a String
        executeAndReturn(() -> "Manoj Kumar Dangi")
                .ifPresent(name -> System.out.println("Result: " + name));

        // Test 2 — failure: supplier throws RuntimeException
        Optional<Object> failed = executeAndReturn(() -> {
            throw new RuntimeException("Something went wrong");
        });
        System.out.println("Was present: " + failed.isPresent());  // false

        // Test 3 — success: valid integer parse
        executeAndReturn(() -> Integer.parseInt("1234"))
                .ifPresent(n -> System.out.println("Parsed: " + n));

        // Test 4 — failure: invalid integer parse (NumberFormatException caught)
        String fallback = executeAndReturn(() -> Integer.parseInt("M1234"))
                .map(String::valueOf)
                .orElse("parse failed — using default");
        System.out.println(fallback);
    }
}