package com.lambdaPractice.typeInference;

public class TypeInferenceExample {

    public static void main(String[] args) {
        StringLengthLambda stringLengthLambda = (s) -> s.length();
        // when there is one input  can remove brackets as well
        StringLengthLambda stringLengthLambda1 = s -> s.length();
        // to implement these lambdas, we need to call the returnStringLength function from these instances above.
        /*----------------------------------*/

        lambdaHelper(s -> s.length()); // or we can also pass the lambda instance made above
    }

    public static void lambdaHelper(StringLengthLambda stringLengthLambda) {
        System.out.println(stringLengthLambda.returnStringLength("Hello world"));
    }

    public interface StringLengthLambda {
        int returnStringLength(String s);
    }
}
