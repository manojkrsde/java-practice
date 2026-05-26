package practice.functionalInterface.p1_lambda;

/*
    Problem 1 — The Basic Task Runner
    Build a TaskRunner class with a method execute(Runnable task) that runs whatever function you pass into it. But add a twist: before executing the task, print "[START] Running task...", and after it finishes, print "[END] Task completed." — so the caller only cares about what to do, not the before/after ceremony.
    Call it three different ways:

    - Pass a lambda directly
    - Pass a method reference
    - Pass a multi-line lambda block
 */

@FunctionalInterface
interface Task1 {
    void execute();
}

public class TaskRunnerCustom {

    // Accepts any Task implementation — lambda, method ref, anonymous class
    static void run(Task1 task) {
        System.out.println("[START] Running task...");
        task.execute();
        System.out.println("[END] Task completed.\n");
    }

    private static void printSystemInfo() {
        System.out.println("OS: " + System.getProperty("os.name"));
    }

    public static void main(String[] args) {

        // Style 1 — lambda assigned to variable
        Task1 greet = () -> System.out.println("Hello from a stored lambda!");
        run(greet);

        // Style 2 — lambda passed inline (more common in production)
        run(() -> System.out.println("Hello from an inline lambda!"));

        // Style 3 — method reference (static method, matches () -> void signature)
        run(TaskRunnerCustom::printSystemInfo);

        // Style 4 — multi-line lambda block
        run(() -> {
            System.out.println("Step 1: validating...");
            System.out.println("Step 2: processing...");
            System.out.println("Step 3: done.");
        });
    }
}