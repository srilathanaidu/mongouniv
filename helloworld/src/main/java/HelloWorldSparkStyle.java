import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Srilatha on 1/10/2017.
 */
public class HelloWorldSparkStyle {

    public static void main(String... args) {
        System.out.println("hello world");
        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return "hello world from spark";
            }
        });
    }
}
