package com.andy.part5_optionals;

public class NullExample1 {
    record User(long id, String firstname, String lastname) {

        String fullName() {
            return String.format("%s %s",
                    firstname(),
                    lastname());
        }

        String initials() {
            return String.format("%s%s",
                    firstname().charAt(0),
                    lastname().charAt(0));
        }
    }

    public static void main(String[] args) {
        var user = new User(42L, "Ben", null);

        System.out.println(user.fullName()); // Ben null

        System.out.println(user.initials()); // NullPointerException
    }

}
