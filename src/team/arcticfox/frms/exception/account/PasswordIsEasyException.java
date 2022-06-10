package team.arcticfox.frms.exception.account;

import team.arcticfox.frms.environment.Environment;
import team.arcticfox.frms.exception.FuRuiException;

public class PasswordIsEasyException extends FuRuiException {
    private static final String CODE = "AC0004";
    private static final String MESSAGE = "Password is too easy.";
    private static final String SOLUTION = "Try more complex character combinations." + Environment.EOL
            + "A valid password consists of 8 to 16 characters, including numbers, " + Environment.EOL
            + "uppercase and lowercase letters and special characters(!,@,#,$,^,&,*,?,(,)).";

    public PasswordIsEasyException() {
        super(CODE, MESSAGE, SOLUTION);
    }
}
