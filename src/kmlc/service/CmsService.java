package kmlc.service;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.util.Timeout;
import example.HelloWorld;
import kmlc.CmsMessages.CmsServiceCode;
import kmlc.CmsMessages.CmsServiceRequest;
import kmlc.CmsMessagesResponse.CmsServiceResultFail;
import kmlc.actors.CmsRestActor;
import kmlc.actors.MasterActor;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import scala.concurrent.Future;
import scala.concurrent.Await;

import static akka.pattern.Patterns.ask;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;

import java.io.*;
import java.util.Properties;


@WebService
public class CmsService  {

    private final String context="This is the Whole world\n";
    private String publishUri;

    public String getPublishUri() {
        return publishUri;
    }

    private ActorRef masterActor;
    private ActorRef cmsRestActor;
    private ActorSystem system;
    private Timeout timeout;
    protected  LoggingAdapter log;


    public void init() throws Exception{
        timeout = Timeout.longToTimeout(5000);
        system = ActorSystem.create("cmsServiceSystem");
        cmsRestActor = system.actorOf(Props.create(CmsRestActor.class));
        masterActor = system.actorOf(Props.create(MasterActor.class,cmsRestActor.path().toString(),timeout));
        log = Logging.getLogger(system, this);
        CmsServiceResultFail result = new CmsServiceResultFail(1,"This is a hello world...");
        log.info(result.toString());


        String rootPath = System.getProperty("user.dir").replace("\\", "/");
        FileInputStream in = new FileInputStream(rootPath+"/conf/CmsService.properties");
        Properties p = new Properties();

        p.load(in);
        publishUri=p.getProperty("publishUri");
        log.info(publishUri);

        in.close();

    }

    /**
     * 获取情报板信息
     * @return
     */
    public String getCmsList() {
        return getCmsServiceResult(CmsServiceCode.GET_SMS_LIST,"");
    }

    public String uploadPlayList(String inXml){
        return getCmsServiceResult(CmsServiceCode.UPLOAD_PLAY_LIST,inXml);
    }

    public String downloadPlayList(String inXml){
        return getCmsServiceResult(CmsServiceCode.DOWNLOAD_PLAY_LIST,inXml);
    }


    public String getCmsServiceResult(int serviceCode,String inXml){
        String result="";
        try{
            Future f = ask(masterActor,new CmsServiceRequest(serviceCode,inXml),timeout);
            result=(String)Await.result(f,timeout.duration());
            System.out.println(result);

        }
        catch (Exception ex){
            ex.printStackTrace();
            log.error("getCmsServiceResult {}",ex.getMessage());
        }
        return result;
    }

    //这里我们使用main方法来发布我们的service
    public static void main(String args[]) throws Exception{

/*
        CommandLineParser parser = new BasicParser( );
        Options options = new Options( );


        options.addOption("c", "Config.Name", true, "Print this usage information");

        CommandLine commandLine = parser.parse(options,args);


        String filename="";
        if( ! commandLine.hasOption('c') ) {
            System.out.println(filename);
            System.exit(0);
        }
*/


        CmsService cmsService = new CmsService();
        cmsService.init();
        Endpoint.publish(cmsService.getPublishUri(),cmsService);
        System.out.println("Publish Success~");
    }
}
