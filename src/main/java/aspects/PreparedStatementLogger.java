package aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Date;

@Aspect
public class PreparedStatementLogger {

    /**
     * Following method will be invoked before actual invocation of #prepareStatement
     * method on any implementation of {@link java.sql.Connection} interface.
     * @param joinPoint Describes join point where the aspect is applied.
     */
    @Before(value = "execution(* java.sql.Connection+.prepareStatement(String,..))")
    public void onPrepareStatement(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        // logging framework of choice may be used instead of System.out
        String sql = (String) args[0];
        System.out.printf(
                "[%s] invoked java.sql.Connection+#prepareStatement with SQL = [%s]\n",
                new Date(), sql
        );
    }

}
