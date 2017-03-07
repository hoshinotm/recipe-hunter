package tests;


public class StartUpStartUpTest {
    public static void main(String[] args) {
        new Thread( () -> javafx.application.Application.launch(StartUpTest.class ) ).start();
        StartUpTest startUpTest = StartUpTest.waitForStartUpTest();
        startUpTest.printSomething();
    }
}