package kmlc.actors;


import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.util.Timeout;
import com.cn.util.XStreamUtil;
import kmlc.CmsMessages.*;

import akka.japi.pf.ReceiveBuilder;
import kmlc.CmsMessagesResponse.CmsDownloadPlayListResult;
import kmlc.CmsMessagesResponse.CmsGetListResult;
import kmlc.CmsMessagesResponse.CmsResult;
import kmlc.CmsMessagesResponse.CmsUploadResult;
import scala.PartialFunction;

import java.util.concurrent.TimeoutException;


//这个actor接受来自webservice客户端传输过的xml数据，封装成相应的json数据，提交到CmsRestActor;
public class MasterActor extends AbstractActor {

    private  ActorSelection cmsRestActor;

    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);
    public MasterActor(String cmsRestActorPath,Timeout timeout){
        System.out.println(cmsRestActorPath);
        cmsRestActor=context().actorSelection(cmsRestActorPath);
    }

    public PartialFunction receive() {
        return ReceiveBuilder.
            match(CmsServiceRequest.class, message -> {

                ActorRef extraActor = buildExtraActor(sender());
                switch (message.getServiceCode()) {
                    case CmsServiceCode.GET_SMS_LIST: {
                        //parseUploadPlayListRequest("");
                        log.info("match CmsServiceRequest {} {}", message.getServiceCode(), message.getSericeContext());
                        cmsRestActor.tell(new GetCmsListRequest(),extraActor);
                    }
                    break;
                    case CmsServiceCode.UPLOAD_PLAY_LIST: {
                        log.info("match CmsServiceRequest {} {}", message.getServiceCode(), message.getSericeContext());
                        UploadPlayListRequest request = parseUploadPlayListRequest(message.getSericeContext());
                        cmsRestActor.tell(request,extraActor);
                        //sender().tell("ccc", self());
                    }
                    break;
                    case CmsServiceCode.DOWNLOAD_PLAY_LIST: {
                        log.info("match CmsServiceRequest {} {}", message.getServiceCode(), message.getSericeContext());
                        DownloadPlayListRequest request = parseDownloadPlayListRequest(message.getSericeContext());
                        cmsRestActor.tell(request, extraActor);
                    }
                    break;
                }
            })
            .matchAny(o -> {
                        log.info("receiver unknow request {} ", o);
                        sender().tell(o,self());
            })
            .build();
    }

    /*
    * 创建一个额外的actor
    *
    * */


    private ActorRef buildExtraActor(ActorRef senderRef){
        class MyActor extends AbstractActor{
            public MyActor(){
                receive(ReceiveBuilder
                        .matchEquals(String.class,x->x.equals("timeout"),x->{
                            senderRef.tell(new Status.Failure(new TimeoutException("timeout")),self());
                        })
                        .match(CmsResult.class,message->{
                            senderRef.tell(message.getResponse(),self());
                        })
                        .match(String.class,message->{
                            senderRef.tell(message,self());
                        })
                        .build()

                );
            }
        }
        return context().actorOf(Props.create(MyActor.class,() -> new MyActor()));
    }



    private static UploadPlayListRequest  parseUploadPlayListRequest(String xml){
        UploadPlayListRequest request=(UploadPlayListRequest) XStreamUtil.xmlToBean(xml);
        return request;
    }
    private static DownloadPlayListRequest parseDownloadPlayListRequest(String xml){
        DownloadPlayListRequest request = (DownloadPlayListRequest) XStreamUtil.xmlToBean(xml);
        return request;
    }
}
