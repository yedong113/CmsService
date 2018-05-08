package kmlc;




import com.cn.util.XStreamUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import kmlc.CmsMessages.*;
import kmlc.CmsMessagesResponse.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;


public class CmsTestMain {


    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    public static void main(String args[]) throws Exception {
        CmsPlaylistInfo cmsPlaylistInfoRequest = new CmsPlaylistInfo();
        cmsPlaylistInfoRequest.setDelay(5);
        cmsPlaylistInfoRequest.setInStyle(1);
        cmsPlaylistInfoRequest.setOutStyle(5);
        cmsPlaylistInfoRequest.setSpeed(5);
        CmsText cmsText = new CmsText();
        cmsText.setFont(2);
        cmsText.setHFont(32);
        cmsText.setColor(2);
        cmsText.setX(0);
        cmsText.setY(0);
        cmsText.setText("进入隧道，减速慢行！");
        CmsText cmsText1 = new CmsText();
        cmsText1.setFont(2);
        cmsText1.setHFont(32);
        cmsText1.setColor(2);
        cmsText1.setX(0);
        cmsText1.setY(0);
        cmsText1.setText("进入隧道，减速慢行啊");
        cmsPlaylistInfoRequest.getTexts().add(cmsText);
        cmsPlaylistInfoRequest.getTexts().add(cmsText1);
        UploadPlayListRequest uploadPlayListRequest = new UploadPlayListRequest();
        uploadPlayListRequest.setId("102001104124205411004");
        uploadPlayListRequest.getPlayList().getInfos().add(cmsPlaylistInfoRequest);
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(uploadPlayListRequest);
        System.out.println(json);

        DownloadPlayListRequest downloadPlayListRequest=new DownloadPlayListRequest();
        downloadPlayListRequest.setId("102001104124205411004");

        json = mapper.writeValueAsString(downloadPlayListRequest);
        System.out.println(json);

        String xmls = XStreamUtil.beanToXml(uploadPlayListRequest);
        System.out.println(xmls);

        UploadPlayListRequest uploadPlayListRequest1 = (UploadPlayListRequest) XStreamUtil.xmlToBean(xmls);
        System.out.println(mapper.writeValueAsString(uploadPlayListRequest1));


        CmsUploadResult result = new CmsUploadResult();
        result.setStatus(1);
        result.setMsg("信息发送成功");

        CmsServiceResultFail resultFail = new CmsServiceResultFail();
        resultFail.setStatus(1);
        resultFail.setMsg("信息发送成功");

        xmls = XStreamUtil.beanToXml(resultFail);
        System.out.println(xmls);

        System.out.println("==================================================");


        result.getData().getPlayList().getInfos().add(cmsPlaylistInfoRequest);

        result.getData().setId("102001104124205411004");
        result.getData().setCmsCtrlId("102001104124205411004.cms.ctrl");
        result.getData().setPlayNo(0);

        json = mapper.writeValueAsString(result);
        //json=json.replace("CmsCtrlId","id");
        System.out.println(json);


        //CmsPlaylistInfo result1 = mapper.readValue(json, getCollectionType(mapper, List.class,CmsText.class));

        String xmlstring = parseUploadPlayListResult(json);
        System.out.println(xmlstring);

        StringBuilder jsonBuild = new StringBuilder();
        jsonBuild.append("{\"status\": 0,\"data\": {},\"msg\": \"信息发送失败\"}");
        xmlstring = parseUploadPlayListResult(jsonBuild.toString());
        System.out.println(xmlstring);
        StringBuilder xmlBuild = new StringBuilder();
        xmlBuild.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuild.append("<CmsUploadResult>");
        xmlBuild.append("<status>1</status>");
        xmlBuild.append("<msg>信息发送失败</msg>");
        xmlBuild.append("</CmsUploadResult>");


        xmlBuild.delete(0,xmlBuild.length());
        xmlBuild.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuild.append("<DownloadPlayListRequest>");
        xmlBuild.append("<id>102001104124205411004</id>");
        xmlBuild.append("</DownloadPlayListRequest>");


        xmls=XStreamUtil.beanToXml(downloadPlayListRequest);
        System.out.println(xmls);

        DownloadPlayListRequest obj=(DownloadPlayListRequest) XStreamUtil.xmlToBean(xmlBuild.toString());


        StringBuilder jsonGetCmsList = new StringBuilder();

        jsonGetCmsList.append("{\"status\":1,\"data\":[{\"deviceCode\":\"102001104124205411004\",\"name\":\"右洞洞内可变情报板2\",\"type\":\"411\",");
        jsonGetCmsList.append("\"lng\":null,\"lat\":null,\"location\":\"102001104124205\",\"stakeNum\":\"K1+788\",\"url\":null,\"state\":1,\"site\":null,");
        jsonGetCmsList.append("\"direction\":null,\"describe\":null,\"width\":96,\"height\":48,\"maxText\":5,\"maxRecord\":5,\"manufacture\":\"\"}],\"msg\":\"获取情报板成功\"}");
        System.out.println(parseGetCmsList(jsonGetCmsList.toString()));

        jsonGetCmsList.delete(0,jsonGetCmsList.length());
        jsonGetCmsList.append("{\"status\":0,\"data\":[{\"deviceCode\":\"102001104124205411004\",\"name\":\"右洞洞内可变情报板2\",\"type\":\"411\",");
        jsonGetCmsList.append("\"lng\":null,\"lat\":null,\"location\":\"102001104124205\",\"stakeNum\":\"K1+788\",\"url\":null,\"state\":1,\"site\":null,");
        jsonGetCmsList.append("\"direction\":null,\"describe\":null,\"width\":96,\"height\":48,\"maxText\":5,\"maxRecord\":5,\"manufacture\":\"\"}],\"msg\":\"获取情报板成功\"}");
        System.out.println(parseGetCmsList(jsonGetCmsList.toString()));


        jsonGetCmsList.delete(0,jsonGetCmsList.length());
        jsonGetCmsList.append("{\"status\":1,\"data\":{\"id\":\"123456789.102001104124205411004.cms.data\",\"Id\":\"102001104124205411004\",\"Playlist\":{");
        jsonGetCmsList.append("\"PlayNo\":0,\"Infos\":[{\"Delay\":5,\"InStyle\":4,\"OutStyle\":1,\"Speed\":5,\"Texts\":[{\"X\":0,\"Y\":0,\"Font\":1,\"HFont\":32,");
        jsonGetCmsList.append("\"Color\":2,\"Text\":\"1进入隧道，减速慢行！\"}],\"Bmps\":[]}]}},\"msg\":\"信息获取成功\"}");

        json=jsonGetCmsList.toString();
        json=json.replace("id","CmsCtrlId");
        System.out.println("++++++++++++++++++++++++++++++++++++");
        System.out.println(parseDownloadPlayListResult(json));

    }

    public static String xml2JSON(String xml) {
        return new XMLSerializer().read(xml).toString();
    }

    public static String parseGetCmsList(String json){
        JSONObject jobj = JSONObject.fromObject(json);

        CmsGetListResult result = new CmsGetListResult();
        result.setStatus(Integer.valueOf(jobj.getString("status").toString()));
        result.setMsg(jobj.getString("msg").toString());

        if (result.getStatus()!=1){
            CmsGetListResultFail resultFail = new CmsGetListResultFail(result.getStatus(),result.getMsg());
            return XStreamUtil.beanToXml(resultFail);
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
        return XStreamUtil.beanToXml(result);
    }


    public static String parseDownloadPlayListResult(String json){
        JSONObject jobj = JSONObject.fromObject(json);
        CmsDownloadPlayListResult result=new CmsDownloadPlayListResult();
        result.setStatus(Integer.valueOf(jobj.getString("status").toString()));
        result.setMsg(jobj.getString("msg").toString());
        if (result.getStatus()!=1){
            CmsUploadResultFail resultFail = new CmsUploadResultFail(result.getStatus(),result.getMsg());
            return XStreamUtil.beanToXml(resultFail);
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
        String xmls = XStreamUtil.beanToXml(result);
        return xmls;
    }


    public static String parseUploadPlayListResult(String json) {
        JSONObject jobj = JSONObject.fromObject(json);
        CmsUploadResult result = new CmsUploadResult();
        result.setStatus(Integer.valueOf(jobj.getString("status").toString()));
        result.setMsg(jobj.getString("msg").toString());
        if (result.getStatus()!=1){
            CmsUploadResultFail resultFail = new CmsUploadResultFail(result.getStatus(),result.getMsg());
            return XStreamUtil.beanToXml(resultFail);
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
                    cmsText.setText(cmsObj.getString("Text").toString());
                    cmsText.setColor(Integer.valueOf(cmsObj.getString("Color").toString()));
                    cmsText.setHFont(Integer.valueOf(cmsObj.getString("HFont").toString()));
                    cmsText.setFont(Integer.valueOf(cmsObj.getString("Font").toString()));
                    cmsPlaylistInfo.getTexts().add(cmsText);
                }
                result.getData().getPlayList().getInfos().add(cmsPlaylistInfo);
            }
        }
        String xmls = XStreamUtil.beanToXml(result);
        return xmls;
    }


}
