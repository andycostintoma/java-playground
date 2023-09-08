package com.andy.part6_design_patterns;

import java.util.function.Consumer;

public class CommandFP {

    // Receiver class
    static class Light {
        void turnOn() {
            System.out.println("Light is ON");
        }

        void turnOff() {
            System.out.println("Light is OFF");
        }
    }

    // Invoker class
    static class RemoteControl {
        private Consumer<Light> command;

        public void setCommand(Consumer<Light> command) {
            this.command = command;
        }

        public void pressButton(Light light) {
            command.accept(light);
        }
    }

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        Light light = new Light();

        // Define command consumers
        Consumer<Light> turnOn = Light::turnOn;
        Consumer<Light> turnOff = Light::turnOff;

        // Set commands for the remote control
        remoteControl.setCommand(turnOn);
        remoteControl.pressButton(light); // Turns on the light

        remoteControl.setCommand(turnOff);
        remoteControl.pressButton(light); // Turns off the light
    }
}
