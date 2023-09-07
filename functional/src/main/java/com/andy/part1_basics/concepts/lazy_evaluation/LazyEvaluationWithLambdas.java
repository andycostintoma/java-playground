package com.andy.part1_basics.concepts.lazy_evaluation;
import java.util.List;
import java.util.function.Supplier;

/**
 * This class demonstrates lazy evaluation and higher-order functions using lambdas in Java.
 * It shows how to update a user with roles in both an eager and a lazy manner.
 */

public class LazyEvaluationWithLambdas {

    record Role(String name) {
    }

    record User(String username) {
    }

    interface DAO {
        List<Role> loadAllAvailableRoles();
    }

    static class FakeDAO implements DAO {
        @Override
        public List<Role> loadAllAvailableRoles() {
            // Simulated expensive database call
            System.out.println("Loading available roles from the database...");
            return List.of(new Role("Admin"), new Role("User"));
        }
    }

    public static void main(String[] args) {
        DAO dao = new FakeDAO(); // Simulated DAO for testing

        var user = new User("john_doe");

        // Eager loading of roles
        updateUserEagerly(user, dao.loadAllAvailableRoles());

        // Lazy loading of roles using a lambda
        updateUserLazily(user, dao::loadAllAvailableRoles);
    }

    static void updateUserEagerly(User user, List<Role> availableRoles) {
        // Logic to update the user with roles
        System.out.println("Updating user: " + user.username());
        System.out.println("Roles:");
        availableRoles.forEach(role -> System.out.println(role.name()));
    }

    static void updateUserLazily(User user, Supplier<List<Role>> availableRolesFn) {
        // Logic to update the user with lazy-loaded roles
        System.out.println("Updating user lazily: " + user.username());
        List<Role> availableRoles = availableRolesFn.get();
        System.out.println("Roles:");
        availableRoles.forEach(role -> System.out.println(role.name()));
    }


}
