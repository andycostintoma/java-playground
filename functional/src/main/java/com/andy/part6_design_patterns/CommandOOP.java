package com.andy.part6_design_patterns;

/**
 * <p>The Command Design Pattern is a behavioral design pattern that encapsulates a request as an object, thereby
 * allowing for parameterization of clients with queues, requests, and operations. It also provides support for
 * undoable operations.</p>
 *
 * <p><b>Intent:</b></p>
 * <ul>
 * <li>Encapsulate a request as an object, thereby allowing for parameterization of clients with queues, requests,
 *     and operations.</li>
 * <li>Provide support for undoable operations.</li>
 * <li>Allow for the support of logging changes and performing transactions.</li>
 * </ul>
 */
public class CommandOOP {

    // Command interface
    interface Command {
        void execute();
    }

    // ConcreteCommand classes
    static class LightOnCommand implements Command {
        private final Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        public void execute() {
            light.turnOn();
        }
    }

    static class LightOffCommand implements Command {
        private final Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        public void execute() {
            light.turnOff();
        }
    }

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
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        public void pressButton() {
            command.execute();
        }
    }

    public static void main(String[] args) {
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(lightOn);
        remoteControl.pressButton(); // Turns on the living room light

        remoteControl.setCommand(lightOff);
        remoteControl.pressButton(); // Turns off the living room light
    }
}
