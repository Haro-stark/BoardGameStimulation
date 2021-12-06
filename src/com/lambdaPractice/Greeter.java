package com.lambdaPractice;

public class Greeter {

    public static void main(String[] args) {

        HelloWorldGreeting helloWorldGreeting = new HelloWorldGreeting();
        Greeter greeter = new Greeter();

        greeter.greet(helloWorldGreeting);

        TestClass1 testClass1 = new TestClass1();
        greeter.test1(testClass1);

        // lambda expression
        Greeting greetLambda = () ->
                System.out.println("We didnt return anything and didnt accept any arguments " +
                        "because the abstract function defined in the interface has exact same signature and return type");
        /*
         * How to call this lambda expression????
         * */
//        greetLambda.perform();
        /*That's it*/

        // Anonymous inner class
        // it will create an instance of another class, rather it's gonna inline instance of
        // the greeting with the implementation being inline.
        // Since We are defining the definintion of the method inside and inline another class instead of making
        // an object of some other class which is implementing the that interface.
        Greeting greetAnonymous = new Greeting() {
            @Override
            public void perform() {
                System.out.println("Hello from Inline Anonymous Inner class of an interface. ");

            }
        };


        /**
         * Now one may think that these anonymous inner class are same as the lambda
         * expression since we are doing almost the same thing. But in real they are not exactly the same things
         * There are things that these inner class does whihc are different from those of lambda exoressuib
         *
         */

        // Now we have our lambda expression and also the anonymous inner fucntion and they both are the instance
        // of the same java interface. So we can pass them both to the function accepting Greeting interface as
        // an argument
        greeter.greet(greetLambda); // or we can send the whole lambda expression instead of this variable only
        greeter.greet(greetAnonymous);
    }

    // the greet method here is accepting a behaviour/action here
    public void greet(Greeting greeting) {
        // since we are calling this method of an interface, it really needs to have a definition somewhere or we can say
        // an implementation of this method. Thus, we will mke a class that should implement this interface and define this method
        greeting.perform();
    }

    public void test1(HelloWorldGreeting HelloWorldGreeting) {
        System.out.println("test1 successful");

    }
}

// we are just ipmlmeneting an interface by just implmenting just the function and not the class

// So lambda are basically used to implement the interface directly at the spot/ at the point inline. We dont have to create
// separate class and then first implement that interface and then write the definitions of that function
// In lambda we just directly write the definition of interface function right at that point and the datatype of that
// function is basically the interface name itself. Just like the example above..
