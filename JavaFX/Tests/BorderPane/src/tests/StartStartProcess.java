package tests;

import core.StartProcess;

/**
 * Created by takayukihoshino on 1/19/17.
 */
public class StartStartProcess {

    public static void main(String[] args) {
        new Thread( () -> javafx.application.Application.launch( StartProcess.class ) ).start();
        StartProcess startProcess = StartProcess.waitForStartProcess();
        startProcess.printSomething();
    }

}
