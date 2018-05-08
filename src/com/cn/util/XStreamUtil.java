package com.cn.util;



import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import kmlc.CmsMessages.*;
import kmlc.CmsMessagesResponse.*;


/**
 * XStream工具类
 * @author sun.kai
 * 2016年8月13日
 */
public class XStreamUtil {

    private static XStream xStream;

    //JVM加载类时会执行这些静态的代码块，如果static代码块有多个，JVM将按照它们在类中出现的先后顺序依次执行它们，每个代码块只会被执行一次。
    static{
        xStream = new XStream(new DomDriver());
        /*
         * 使用xStream.alias(String name, Class Type)为任何一个自定义类创建到类到元素的别名
         * 如果不使用别名，则生成的标签名为类全名 CmsPlaylistInfoRequest
         */
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{CmsInfo.class, CmsInfo.class});
        xStream.allowTypes(new Class[]{CmsText.class, CmsText.class});
        xStream.allowTypes(new Class[]{CmsPlaylistInfo.class, CmsPlaylistInfo.class});
        xStream.allowTypes(new Class[]{UploadPlayListRequest.class, UploadPlayListRequest.class});
        xStream.allowTypes(new Class[]{CmsUploadResult.class, CmsUploadResult.class});
        xStream.allowTypes(new Class[]{CmsPlayList.class, CmsPlayList.class});//CmsPlaylistInfo
        xStream.allowTypes(new Class[]{CmsGetListResult.class, CmsGetListResult.class});//CmsPlaylistInfo
        xStream.allowTypes(new Class[]{DownloadPlayListRequest.class, DownloadPlayListRequest.class});//CmsPlaylistInfo

        xStream.alias("CmsInfo", CmsInfo.class);
        xStream.alias("UploadPlayListRequest", UploadPlayListRequest.class);
        xStream.alias("CmsPlaylistInfo", CmsPlaylistInfo.class);
        xStream.alias("CmsText", CmsText.class);
        xStream.alias("CmsUploadResult", CmsUploadResult.class);
        xStream.alias("CmsUploadResult", CmsUploadResultFail.class);

        xStream.alias("CmsGetListResult", CmsGetListResult.class);
        xStream.alias("CmsDeviceInfo", CmsDeviceInfo.class);
        xStream.alias("CmsGetListResultFail", CmsGetListResultFail.class);

        xStream.alias("CmsGetListResultFail", CmsGetListResultFail.class);
        xStream.alias("DownloadPlayListRequest", DownloadPlayListRequest.class);
        xStream.alias("CmsDownloadPlayListResult", CmsDownloadPlayListResult.class);
        xStream.alias("CmsServiceResultFail", CmsServiceResultFail.class);

    }

    //xml转java对象
    public static Object xmlToBean(String xml){
        return xStream.fromXML(xml);
    }

    //java对象转xml
    public static String beanToXml(Object obj){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(obj);
    }
}