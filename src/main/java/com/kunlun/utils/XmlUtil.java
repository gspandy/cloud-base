package com.kunlun.utils;

import com.kunlun.wxentity.OrderQueryResponseData;
import com.kunlun.wxentity.UnifiedOrderNotifyRequestData;
import com.kunlun.wxentity.UnifiedOrderResponseData;
import com.kunlun.wxentity.WxRefundNotifyResponseData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import java.io.Serializable;
import java.util.Map;

/**
 * XML转换工具类
 *
 * @author by kunlun
 * @version <0.1>
 * @created on 2017/12/13.
 */
public class XmlUtil implements Serializable {

    private static final long serialVersionUID = 3802299821120298177L;



    /**
     * 把XML字符串转换为统一下单接口返回数据对象
     *
     * @param responseString
     * @return return:UnifiedOrderResponseData
     */
    public static UnifiedOrderResponseData castXMLStringToUnifiedOrderResponseData(String responseString) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("xml", UnifiedOrderResponseData.class);
        xStream.processAnnotations(UnifiedOrderResponseData.class);
        return (UnifiedOrderResponseData) xStream.fromXML(responseString);
    }


    /**
     * 把XML字符串转换为统一下单回调接口请求数据对象
     *
     * @param requestString
     * @return return:UnifiedOrderNotifyRequestData
     */
    public static UnifiedOrderNotifyRequestData castXMLStringToUnifiedOrderNotifyRequestData(String requestString) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("xml", UnifiedOrderNotifyRequestData.class);
        return (UnifiedOrderNotifyRequestData) xStream.fromXML(requestString);
    }

    /**
     * 把XML字符串转换为退款返回对象
     *
     * @param responseString
     * @return
     */
    public static WxRefundNotifyResponseData castXMLStringToWxRefundNotifyResponseData(String responseString) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("xml", WxRefundNotifyResponseData.class);
        xStream.processAnnotations(WxRefundNotifyResponseData.class);
        return (WxRefundNotifyResponseData) xStream.fromXML(responseString);
    }

    /**
     * 把XML字符串转换为订单查询接口返回数据对象
     *
     * @param responseString
     * @return return:OrderQueryResponseData
     */
    public static OrderQueryResponseData castXMLStringToOrderQueryResponseData(String responseString) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("xml", OrderQueryResponseData.class);
        return (OrderQueryResponseData) xStream.fromXML(responseString);
    }

    /**
     * Map 转 xml
     *
     * @param map
     * @return
     */
    public static String mapConvertToXML(Map<String, Object> map) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<xml>");
        for (String key : map.keySet()) {
            stringBuffer.append("<" + key + ">" + map.get(key) + "</" + key + ">");
        }
        stringBuffer.append("</xml>");
        return stringBuffer.toString();
    }

    /**
     * 将对象转成XML流字符串
     *
     * @param object
     * @return
     */
    public static  String castDataToXMLString(Object object){
        XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        return xStream.toXML(object);
    }

}
