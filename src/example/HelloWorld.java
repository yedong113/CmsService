package example;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;



@WebService
public class HelloWorld {

  public String sayHelloWorldFrom(String from) {
    String result = "Hello, world, from " + from;
    System.out.println(result);
    return result;
  }
  //这里我们使用main方法来发布我们的service
  public static void main(String args[]){
    Endpoint.publish("http://localhost:9001/Service/HelloWorld",new HelloWorld());
    System.out.println("Publish Success~");
  }
}
