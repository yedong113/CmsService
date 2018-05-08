package kmlc;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import kmlc.CmsMessages.CmsServiceCode;
import kmlc.CmsMessages.CmsServiceRequest;
import kmlc.actors.CmsRestActor;
import kmlc.actors.MasterActor;

import scala.concurrent.Future;
import scala.concurrent.Await;

import static akka.pattern.Patterns.ask;

public class TestActorMain {
    public static void main(String args[]) throws Exception{
        Timeout timeout = Timeout.longToTimeout(10000);
        ActorSystem system = ActorSystem.create("cmsServiceSystem");
        ActorRef cmsRestActor = system.actorOf(Props.create(CmsRestActor.class));

        String xmlUpload="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<UploadPlayListRequest>\n" +
                "  <id>102001104124205411004</id>\n" +
                "  <playList>\n" +
                "    <Infos>\n" +
                "      <CmsPlaylistInfo>\n" +
                "        <Delay>5</Delay>\n" +
                "        <InStyle>1</InStyle>\n" +
                "        <OutStyle>5</OutStyle>\n" +
                "        <Speed>5</Speed>\n" +
                "        <Texts>\n" +
                "          <CmsText>\n" +
                "            <Font>2</Font>\n" +
                "            <HFont>32</HFont>\n" +
                "            <Color>2</Color>\n" +
                "            <Text>进入隧道，减速慢行！</Text>\n" +
                "            <X>0</X>\n" +
                "            <Y>0</Y>\n" +
                "          </CmsText>\n" +
                "          <CmsText>\n" +
                "            <Font>2</Font>\n" +
                "            <HFont>32</HFont>\n" +
                "            <Color>2</Color>\n" +
                "            <Text>进入隧道，减速慢行啊</Text>\n" +
                "            <X>0</X>\n" +
                "            <Y>0</Y>\n" +
                "          </CmsText>\n" +
                "        </Texts>\n" +
                "      </CmsPlaylistInfo>\n" +
                "    </Infos>\n" +
                "  </playList>\n" +
                "</UploadPlayListRequest>";


/*        xmlUpload="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<DownloadPlayListRequest>\n" +
                "  <id>102001104124205411004</id>\n" +
                "</DownloadPlayListRequest>";*/

        ActorRef masterActor = system.actorOf(Props.create(MasterActor.class,cmsRestActor.path().toString(),timeout));
        //masterActor.tell(new CmsServiceRequest(CmsServiceCode.UPLOAD_PLAY_LIST,xmlUpload),masterActor);


        //Future f = ask(masterActor,new CmsServiceRequest(CmsServiceCode.UPLOAD_PLAY_LIST,xmlUpload),timeout);

        //String result=(String)Await.result(f,timeout.duration());

       // System.out.println("result: "+result);



    }
}
