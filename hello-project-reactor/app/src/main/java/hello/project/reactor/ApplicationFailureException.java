package hello.project.reactor;

import java.util.function.Function;

public class ApplicationFailureException extends Exception {
    public ApplicationFailureException(String s) {
        super(s);
    }
}
