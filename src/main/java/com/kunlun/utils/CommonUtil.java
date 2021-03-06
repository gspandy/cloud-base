package com.kunlun.utils;

import com.github.pagehelper.util.StringUtil;
import com.kunlun.entity.*;
import com.kunlun.enums.CommonEnum;
import com.kunlun.wxentity.UnifiedRequestData;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author JackSpeed
 * @version V1.0 <公共工具>
 * @date 17-12-20下午2:09
 * @desc
 */
public class CommonUtil {
    /**
     * 商品快照组装
     *
     * @param goods
     * @param goodId
     * @return
     */
    public static GoodSnapshot snapshotConstructor(Good goods, Long goodId) {
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        BeanUtils.copyProperties(goods, goodSnapshot);
        goodSnapshot.setGoodId(goodId);
        goodSnapshot.setGoodDescription(goods.getDescription());
        return goodSnapshot;
    }


    public static String formatDate(String targetDateString) {
        if (targetDateString == null) {
            return null;
        }
        if (targetDateString.contains(" 0800 (中国标准时间)")) {
            targetDateString = targetDateString.replace(" 0800 (中国标准时间)", "+08:00");
        } else {
            return targetDateString;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss", Locale.ENGLISH);
        try {
            Date tmp2 = sdf.parse(targetDateString);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            targetDateString = sdf2.format(tmp2) + " 23:59:59";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetDateString;
    }

    /**
     * 计算年龄和性别
     *
     * @param idCardNo String
     */
    public static String getGenderByIdCardNo(String idCardNo) {
        if (StringUtil.isEmpty(idCardNo)) {
            return null;
        }
        try {
            int leg = idCardNo.length();
            if (leg != 18 && leg != 15) {
                return null;
            } else if (leg == 18 && Integer.parseInt(idCardNo.substring(16).substring(0, 1)) % 2 == 0) {
                return "FEMALE";
            } else if (leg == 15 && Integer.parseInt(idCardNo.substring(14, 15)) % 2 == 0) {
                return "FEMALE";
            }
            return "MALE";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算年龄和性别
     *
     * @param idCardNo String
     */
    public static int getAgeByIdCardNo(String idCardNo) {
        if (StringUtil.isEmpty(idCardNo)) {
            return 0;
        }
        try {
            int leg = idCardNo.length();
            if (leg == 18) {
                String dates = idCardNo.substring(6, 10);
                SimpleDateFormat df = new SimpleDateFormat("yyyy");
                String year = df.format(new Date());
                return Integer.parseInt(year) - Integer.parseInt(dates);
            } else {
                String dates = "19" + idCardNo.substring(6, 8);
                SimpleDateFormat df = new SimpleDateFormat("yyyy");
                String year = df.format(new Date());
                return Integer.parseInt(year) - Integer.parseInt(dates);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 构造订单
     *
     * @param good
     * @param goodSnapShotId
     * @param unifiedRequestData
     * @param delivery
     * @param userId
     * @return
     */
    public static Order constructOrder(Good good,
                                       Long goodSnapShotId,
                                       UnifiedRequestData unifiedRequestData,
                                       Delivery delivery,
                                       String userId) {
        Order order = new Order();
        BeanUtils.copyProperties(order, delivery);
        //构建订单,仍未支付
        order.setPayType("");
        order.setOrderType(CommonEnum.MOBILE_ORDER.getCode());
        //设置为待付款
        order.setOrderStatus(CommonEnum.UN_PAY.getCode());
        order.setGoodName(good.getGoodName());
        order.setDeliveryId(delivery.getId());
        order.setOperatePoint(unifiedRequestData.getPoint());
        order.setUseTicket(unifiedRequestData.getUseTicket());
        order.setOrderFee(unifiedRequestData.getOrderFee());
        order.setGoodFee(unifiedRequestData.getGoodFee());
        order.setReduceFee(unifiedRequestData.getReduceFee());
        order.setFreightFee(unifiedRequestData.getFreightFee());
        order.setPaymentFee(unifiedRequestData.getPaymentFee());
        order.setCount(unifiedRequestData.getCount());
        order.setMessage(unifiedRequestData.getMessage());
        order.setGoodId(good.getId());
        order.setSellerId(good.getSellerId());
        //是否使用优惠券
        if (CommonEnum.USE_TICKET.getCode().equals(order.getUseTicket())) {
            order.setTicketId(unifiedRequestData.getTicketId());
        }
        //是否使用积分
        if (unifiedRequestData.getPoint() <= 0) {
            order.setUsePoint(CommonEnum.NOT_USE_POINT.getCode());
        } else {
            order.setUsePoint(CommonEnum.USE_POINT.getCode());
        }
        //设置用户id,商品快照id,商户id,商户id,商品编号
        order.setGoodSnapshotId(goodSnapShotId);
        order.setUserId(userId);
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setSellerId(good.getSellerId());

        return order;
    }


    /**
     * 组装订单日志
     *
     * @param orderNo
     * @param action
     * @param ipAddress
     * @param orderId
     * @return
     */
    public static OrderLog constructOrderLog(String orderNo, String action, String ipAddress, Long orderId) {
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderNo(orderNo);
        orderLog.setAction(action);
        orderLog.setIpAddress(ipAddress);
        orderLog.setOrderId(orderId);
        return orderLog;
    }

    /**
     * 组装商品日志
     *
     * @param goodId
     * @param goodName
     * @param action
     * @return
     */
    public static GoodLog constructGoodLog(Long goodId, String goodName, String action) {
        GoodLog goodLog = new GoodLog();
        goodLog.setGoodId(goodId);
        goodLog.setGoodName(goodName);
        goodLog.setAction(action);
        return goodLog;
    }

    /**
     * 构建积分日志
     *
     * @param userId
     * @param operatPoint
     * @param currentPoint
     * @return
     */
    public static PointLog constructPointLog(String userId, Integer operatPoint, Integer currentPoint) {
        PointLog pointLog = new PointLog();
        pointLog.setUserId(userId);
        pointLog.setOperatePoint(operatPoint);
        //查询用户现有积分
        pointLog.setCurrentPoint(currentPoint);
        //判断积分操作
        String action = operatPoint > 0 ? CommonEnum.ADD.getCode() : CommonEnum.SUBTRACT.getCode();
        pointLog.setAction(action);
        return pointLog;
    }


    public static Good constructGood(Long goodId, int stock) {
        Good good = new Good();
        good.setId(goodId);
        good.setStock(stock);
        return good;
    }


}
