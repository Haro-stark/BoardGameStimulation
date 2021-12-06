package com.runnable;

import com.lambdaPractice.Greeting;

public class RunnableExample implements Greeting {

    public static void main(String[] args) {
        // creating a new thread and ask it an instance of runnable
        Thread myThread = new Thread(new Runnable() {
            // since runnable is an interface which is required by a thread to run. So we are basically implmenting the only
            // method of this class or we can say overriding it.
            @Override
            public void run() {
                System.out.println("printed inside runnable!!");
            }
        });
        myThread.run();

        /*+---------------------------+*/
        /*
        We kmnow that Runnable is an interface which has only single function that needs to be overridden.
        * Now instead of doing this, we can also pass a lambda expression that has a similar  nature as that of Runnable interface
        Because lambda is also an interface with only single method. Thus, we can now change it to:*/
        Thread lambdaThread = new Thread(()-> System.out.println("Printing inside thread with lambda expression"));
        lambdaThread.run();
        final int c = 5;
        Thread lambdaThread2 = new Thread(()-> {
            int a=5, b=9;
        });
        lambdaThread2.run();

    }

    @Override
    public void perform() {

    }
}
