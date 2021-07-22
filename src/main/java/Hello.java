import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hello {

    static final Logger logger =
            LoggerFactory.getLogger(Hello.class);

    public static void main(String[] arg0) {
        System.out.println("hello");

        Hello me = new Hello();
        me.go();
    }

    private void go() {
        SudokuGrid grid = new SudokuGrid();
        logger.info(grid.toString());


    }

}
