/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verkkolelu.tools.commandable;

/**
 * A thread that has the protected method pause() that can be called from 
 * inside the run() method. Then the public method resumeThread is called, and
 * execution can continue.
 * @author mikko
 */
public class StepThread extends Thread{
    private volatile boolean running;
    

    /**
     * Wakes up the thread.
     */
    public void resumeThread() {
        running = true;
    }

    /**
     * Pauses the thread.
     */
    protected void pause() {
        running = false;
        while (!running) {
        }
    }
    
}
