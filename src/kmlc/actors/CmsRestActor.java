package kmlc.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.cn.util.XStreamUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import kmlc.CmsMessages.*;
import kmlc.CmsMessagesResponse.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import scala.PartialFunction;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CmsRestActor extends AbstractActor {
    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);
    private String getCmsListUri="";

    private String uploadPlayListUri="";

    private String downloadPlayListUri="";

    private final ActorRef masterActor = null;

    public CmsRestActor(){
        init();
    }


    public PartialFunction receive() {
        return ReceiveBuilder.
                match(GetCmsListRequest.class, message -> {
                    log.info("GetCmsListRequest");
                    //String jsonResult = getCmsList();
                    //sender().tell(parseGetCmsList(jsonResult),self());
                    String xmlResult="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<CmsServiceResultFail>\n" +
                            "  <status>1</status>\n" +
                            "  <msg>信息发送成功</msg>\n" +
                            "</CmsServiceResultFail>";
                    sender().tell(new CmsResult(CmsServiceCode.UPLOAD_PLAY_LIST,xmlResult),self());

                })
                .match(UploadPlayListRequest.class,message->{//下发控制
                    //uploadPlayList(message);
                    ObjectMapper mapper = new ObjectMapper();
                    String json = mapper.writeValueAsString(message);
                    //String jsonResult = uploadPlayList(message);
                    //sender().tell(parseUploadPlayListResult(jsonResult),self());
                    log.info(json);
                    String xmlResult="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<UploadPlayListRequest>\n" +
                            "  <id>102001104124205411004</id>\n" +
                            "  <playList>\n" +
                            "    <Infos>\n" +
                            "      <CmsPlaylistInfoRequest>\n" +
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
                            "      </CmsPlaylistInfoRequest>\n" +
                            "    </Infos>\n" +
                            "  </playList>\n" +
                            "</UploadPlayListRequest>";
                    sender().tell(new CmsResult(CmsServiceCode.UPLOAD_PLAY_LIST,xmlResult),self());
                })
                .match(DownloadPlayListRequest.class,message->{
                    ObjectMapper mapper = new ObjectMapper();
                    String json = mapper.writeValueAsString(message);
//                    String jsonResult = downloadPlayList(message);
//                    sender().tell(parseDownloadPlayListResult(jsonResult),self());
                    String xmlResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<CmsDownloadPlayListResult>\n" +
                            "  <status>1</status>\n" +
                            "  <data>\n" +
                            "    <CmsCtrlId>123456789.102001104124205411004.cms.data</CmsCtrlId>\n" +
                            "    <Id>102001104124205411004</Id>\n" +
                            "    <Playlist>\n" +
                            "      <Infos>\n" +
                            "        <CmsPlaylistInfo>\n" +
                            "          <Delay>5</Delay>\n" +
                            "          <InStyle>4</InStyle>\n" +
                            "          <OutStyle>1</OutStyle>\n" +
                            "          <Speed>5</Speed>\n" +
                            "          <Texts>\n" +
                            "            <CmsText>\n" +
                            "              <Font>1</Font>\n" +
                            "              <HFont>32</HFont>\n" +
                            "              <Color>2</Color>\n" +
                            "              <Text>1进入隧道，减速慢行！</Text>\n" +
                            "              <X>0</X>\n" +
                            "              <Y>0</Y>\n" +
                            "            </CmsText>\n" +
                            "          </Texts>\n" +
                            "        </CmsPlaylistInfo>\n" +
                            "      </Infos>\n" +
                            "    </Playlist>\n" +
                            "    <PlayNo>0</PlayNo>\n" +
                            "  </data>\n" +
                            "  <msg>信息获取成功</msg>\n" +
                            "</CmsDownloadPlayListResult>";

                    sender().tell(new CmsResult(CmsServiceCode.DOWNLOAD_PLAY_LIST,xmlResult),self());

                })
                .matchAny(o->{
                    log.info("matchAny {}",o);
                })
                .build();
    }

    public void init(){
        try
        {
            String rootPath = System.getProperty("user.dir").replace("\\", "/");
            String fileName = "/conf/CmsService.properties";
            Properties p = new Properties();
            InputStream is = new FileInputStream(new File(rootPath+fileName));
            p.load(is);
            getCmsListUri=p.getProperty("getCmsListUri");
            uploadPlayListUri=p.getProperty("uploadPlayListUri");
            downloadPlayListUri=p.getProperty("downloadPlayListUri");
            log.info("{} {} {}",getCmsListUri,uploadPlayListUri,downloadPlayListUri);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    private String getCmsList() throws Exception{
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(getCmsListUri);
        HttpResponse response = httpClient.execute(request);
        String strResult="";
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            strResult = EntityUtils.toString(response.getEntity());
        }
        else {
            strResult="{\"status\": 2,\t\"data\": [],\t\"msg\": \"请求的服务不存在\"}";
        }
        return strResult;
    }

    private CmsResult parseGetCmsList(String json){
        JSONObject jobj = JSONObject.fromObject(json);

        CmsGetListResult result = new CmsGetListResult();
        result.setStatus(Integer.valueOf(jobj.getString("status").toString()));
        result.setMsg(jobj.getString("msg").toString());

        if (result.getStatus()!=1){
            return new CmsResult(CmsServiceCode.GET_SMS_LIST,XStreamUtil.beanToXml(new CmsServiceResultFail(result.getStatus(),result.getMsg())));
        }

        JSONArray jsonData = jobj.getJSONArray("data");
        for (int i=0;i<jsonData.size();i++){
            JSONObject objDeviceInfo = jsonData.getJSONObject(i);
            System.out.println(objDeviceInfo);
            CmsDeviceInfo deviceInfo = new CmsDeviceInfo();
            deviceInfo.setDeviceCode(objDeviceInfo.getString("deviceCode"));
            deviceInfo.setName(objDeviceInfo.getString("name"));
            deviceInfo.setType(objDeviceInfo.getString("type"));
            deviceInfo.setLng(objDeviceInfo.getString("lng"));
            deviceInfo.setLat(objDeviceInfo.getString("lat"));
            deviceInfo.setLocation(objDeviceInfo.getString("location"));
            deviceInfo.setStakeNum(objDeviceInfo.getString("stakeNum"));
            deviceInfo.setState(objDeviceInfo.getString("state"));
            deviceInfo.setDirection(objDeviceInfo.getString("direction"));
            deviceInfo.setDescribe(objDeviceInfo.getString("describe"));
            deviceInfo.setWidth(Integer.valueOf(objDeviceInfo.getString("width").toString()));
            deviceInfo.setHeight(Integer.valueOf(objDeviceInfo.getString("height").toString()));
            deviceInfo.setMaxText(Integer.valueOf(objDeviceInfo.getString("maxText").toString()));
            deviceInfo.setMaxRecord(Integer.valueOf(objDeviceInfo.getString("maxRecord").toString()));
            deviceInfo.setManufacture(objDeviceInfo.getString("manufacture"));
            result.getData().add(deviceInfo);
        }
        return new CmsResult(CmsServiceCode.GET_SMS_LIST,XStreamUtil.beanToXml(result));
    }


    private String uploadPlayList(UploadPlayListRequest obj) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        StringEntity requestEntity = new StringEntity(json);
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(uploadPlayListUri);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");
        request.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
            String jsonResponse = EntityUtils.toString(response.getEntity());
            return jsonResponse;
        }
        return "{\"status\": 2,\t\"data\": [],\t\"msg\": \"请求的服务不存在\"}";
    }


    /**
     *
     * @param json
     * @return
     */
    private CmsResult parseUploadPlayListResult(String json) {
        JSONObject jobj = JSONObject.fromObject(json);
        CmsUploadResult result = new CmsUploadResult();
        result.setStatus(Integer.valueOf(jobj.getString("status").toString()));
        result.setMsg(jobj.getString("msg").toString());
        if (result.getStatus()!=1){
            return new CmsResult(CmsServiceCode.UPLOAD_PLAY_LIST,XStreamUtil.beanToXml(new CmsServiceResultFail(result.getStatus(),result.getMsg())));
        }
        JSONObject dataObj = jobj.getJSONObject("data");
        result.getData().setId(dataObj.getString("Id"));
        result.getData().setCmsCtrlId(dataObj.getString("CmsCtrlId"));
        result.getData().setPlayNo(Integer.valueOf(dataObj.getString("PlayNo").toString()));
        JSONObject playListObj = dataObj.getJSONObject("Playlist");
        if (playListObj.size()>0){
            JSONArray infosObj = playListObj.getJSONArray("Infos");
            for (int i=0;i<infosObj.size();i++){
                JSONObject infobj = infosObj.getJSONObject(i);

                CmsPlaylistInfo cmsPlaylistInfo = new CmsPlaylistInfo();
                cmsPlaylistInfo.setDelay(Integer.valueOf(infobj.getString("Delay").toString()));
                cmsPlaylistInfo.setInStyle(Integer.valueOf(infobj.getString("InStyle").toString()));
                cmsPlaylistInfo.setOutStyle(Integer.valueOf(infobj.getString("OutStyle").toString()));
                cmsPlaylistInfo.setSpeed(Integer.valueOf(infobj.getString("Speed").toString()));
                JSONArray cmsTexts = infobj.getJSONArray("Texts");
                for (int j=0;j<cmsTexts.size();j++){
                    JSONObject cmsObj = cmsTexts.getJSONObject(j);
                    CmsText cmsText = new CmsText();
                    cmsText.setY(0);
                    cmsText.setX(0);
                    cmsText.setText(cmsObj.getString("Text"));
                    cmsText.setColor(Integer.valueOf(cmsObj.getString("Color")));
                    cmsText.setHFont(Integer.valueOf(cmsObj.getString("HFont")));
                    cmsText.setFont(Integer.valueOf(cmsObj.getString("Font")));
                    cmsPlaylistInfo.getTexts().add(cmsText);
                }
                result.getData().getPlayList().getInfos().add(cmsPlaylistInfo);
            }
        }
        return new CmsResult(CmsServiceCode.UPLOAD_PLAY_LIST,XStreamUtil.beanToXml(result));
    }

    /**
     * 调用公司Restful服务得到当前播放列表
     * @param obj  输入参数 {"id":"设备ID"}
     * @return  Restful返回的json字符串
     * @throws Exception
     */
    private String downloadPlayList(DownloadPlayListRequest obj) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        StringEntity requestEntity = new StringEntity(json);
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(downloadPlayListUri);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");
        request.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
            String jsonResponse = EntityUtils.toString(response.getEntity());
            return jsonResponse;
        }
        return "{\"status\": 2,\t\"data\": [],\t\"msg\": \"请求的服务不存在\"}";
    }


    /**
     * 解析当前播放列表，再转换为webService接口需要的xml
     * @param json
     * @return
     */
    private CmsResult parseDownloadPlayListResult(String json){
        JSONObject jobj = JSONObject.fromObject(json);
        CmsDownloadPlayListResult result=new CmsDownloadPlayListResult();
        result.setStatus(Integer.valueOf(jobj.getString("status").toString()));
        result.setMsg(jobj.getString("msg").toString());
        if (result.getStatus()!=1){
            return new CmsResult(CmsServiceCode.DOWNLOAD_PLAY_LIST,XStreamUtil.beanToXml(new CmsServiceResultFail(result.getStatus(),result.getMsg())));
        }
        JSONObject dataObj = jobj.getJSONObject("data");
        result.getData().setId(dataObj.getString("Id"));
        result.getData().setCmsCtrlId(dataObj.getString("CmsCtrlId"));
        result.getData().setPlayNo(0);
        JSONObject playListObj = dataObj.getJSONObject("Playlist");
        if (playListObj.size()>0){
            JSONArray infosObj = playListObj.getJSONArray("Infos");
            for (int i=0;i<infosObj.size();i++){
                JSONObject infobj = infosObj.getJSONObject(i);

                CmsPlaylistInfo cmsPlaylistInfo = new CmsPlaylistInfo();
                cmsPlaylistInfo.setDelay(Integer.valueOf(infobj.getString("Delay").toString()));
                cmsPlaylistInfo.setInStyle(Integer.valueOf(infobj.getString("InStyle").toString()));
                cmsPlaylistInfo.setOutStyle(Integer.valueOf(infobj.getString("OutStyle").toString()));
                cmsPlaylistInfo.setSpeed(Integer.valueOf(infobj.getString("Speed").toString()));
                JSONArray cmsTexts = infobj.getJSONArray("Texts");
                for (int j=0;j<cmsTexts.size();j++){
                    JSONObject cmsObj = cmsTexts.getJSONObject(j);
                    CmsText cmsText = new CmsText();
                    cmsText.setY(0);
                    cmsText.setX(0);
                    cmsText.setText(cmsObj.getString("Text").toString());
                    cmsText.setColor(Integer.valueOf(cmsObj.getString("Color").toString()));
                    cmsText.setHFont(Integer.valueOf(cmsObj.getString("HFont").toString()));
                    cmsText.setFont(Integer.valueOf(cmsObj.getString("Font").toString()));
                    cmsPlaylistInfo.getTexts().add(cmsText);
                }
                result.getData().getPlayList().getInfos().add(cmsPlaylistInfo);
            }
        }
        return new CmsResult(CmsServiceCode.DOWNLOAD_PLAY_LIST, XStreamUtil.beanToXml(result));
    }
}
