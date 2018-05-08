package kmlc;


import com.cn.util.XStreamUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import kmlc.CmsMessages.CmsInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class TestMain {
    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static void main(String args[]) {

        try {
            String fileName = "E:/CmsService.properties";
            Properties p = new Properties();
            InputStream is = new FileInputStream(new File(fileName));
            p.load(is);
            System.out.println(p.getProperty("getCmsDeviceListUri"));
            CmsInfo cmsInfo = new CmsInfo();
            cmsInfo.setId("873984885");
            cmsInfo.setName("name");
            cmsInfo.setHeight(12);
            cmsInfo.setWidth(10);
            cmsInfo.setLat(100.21f);
            cmsInfo.setLng(100.11f);
            cmsInfo.setLocation("Location");
            cmsInfo.setStakeId("StakeId");
            cmsInfo.setUrl("url");

            CmsInfo cmsInfo1 = new CmsInfo();
            cmsInfo1.setId("873984885");
            cmsInfo1.setName("name");
            cmsInfo1.setHeight(12);
            cmsInfo1.setWidth(10);
            cmsInfo1.setLat(100.21f);
            cmsInfo1.setLng(100.11f);
            cmsInfo1.setLocation("Location");
            cmsInfo1.setStakeId("StakeId");
            cmsInfo1.setUrl("url");

            System.out.println(cmsInfo.toString());
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("++++++++++++");
            String json = mapper.writeValueAsString(cmsInfo);
            System.out.println(json);
            HttpClient httpClient = new DefaultHttpClient();

            StringEntity  requestEntity = new StringEntity(json);

            HttpPost request = new HttpPost("http://localhost:3000/light/paramer");
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Accept", "application/json");
            request.setEntity(requestEntity);

            HttpResponse response = httpClient.execute(request);
            String jsonResponse = EntityUtils.toString(response.getEntity());
            System.out.print("addResource result is : " + jsonResponse + "\n");


            List<CmsInfo> cmsInfoList = new ArrayList<CmsInfo>();

            cmsInfoList.add(cmsInfo);
            cmsInfoList.add(cmsInfo1);

            String xmls = XStreamUtil.beanToXml(cmsInfoList);
            System.out.println(xmls);


            String xml = XStreamUtil.beanToXml(cmsInfo);
            System.out.println(xml);

            CmsInfo person2 = (CmsInfo) XStreamUtil.xmlToBean(xml);
            System.out.println(person2.toString());


            System.out.println("++++++++++++");

            CmsInfo parseCmsInfo = mapper.readValue(json, CmsInfo.class);

            System.out.println(parseCmsInfo.toString());

            String jsonlist = mapper.writeValueAsString(cmsInfoList);
            System.out.println(jsonlist);

            List<CmsInfo> parseCmsInfoList = new ArrayList<CmsInfo>();
            parseCmsInfoList = mapper.readValue(jsonlist, getCollectionType(mapper, List.class, CmsInfo.class));
            System.out.println(jsonlist);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
