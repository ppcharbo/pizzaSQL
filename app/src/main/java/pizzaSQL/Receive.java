package pizzaSQL;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/order")
public class Receive {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void makeOrder(Order order){
        System.out.println(order);
    }
}
