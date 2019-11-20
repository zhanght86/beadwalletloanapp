package com.waterelephant.operatorData.ryt.servier.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterelephant.operatorData.dto.OperatorBillDataDto;
import com.waterelephant.operatorData.dto.OperatorFamilyDataDto;
import com.waterelephant.operatorData.dto.OperatorMonthInfoDto;
import com.waterelephant.operatorData.dto.OperatorMsgDataDto;
import com.waterelephant.operatorData.dto.OperatorNetDataDto;
import com.waterelephant.operatorData.dto.OperatorNetLogDataDto;
import com.waterelephant.operatorData.dto.OperatorRechargeDataDto;
import com.waterelephant.operatorData.dto.OperatorTelDataDto;
import com.waterelephant.operatorData.dto.OperatorUserDataDto;
import com.waterelephant.operatorData.ryt.mapper.RytOperatorDataMapper;
import com.waterelephant.operatorData.service.impl.OperatorsDataAbstractService;
import com.waterelephant.utils.DateUtil;
@Service
public class RytOperatorDataImpl extends OperatorsDataAbstractService{
	
	private Logger logger = LoggerFactory.getLogger(RytOperatorDataImpl.class);
	
	@Autowired
	private RytOperatorDataMapper mapper;

	@Override
	public OperatorUserDataDto getUserData(Long borrowerId, Long orderId) {
		logger.info("----【容易推】----依据orderId:{}查询用户基本信息",orderId);
		return this.mapper.queryUserData(orderId);
	}

	@Override
	public List<OperatorRechargeDataDto> getRechargeDataList(Long borrowerId,
			Long orderId) {
		
		logger.info("----【容易推】根据borrowerId:{},orderId:{}获取充值记录信息----",borrowerId,orderId);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		calendar.add(Calendar.MONTH, -5);
		String startTime = DateUtil.getDateString(calendar.getTime(), "yyyy-MM-dd");//开始日期为每月1号零点
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		String endTime  = DateUtil.getDateString(calendar.getTime(), "yyyy-MM-dd");//结束日期为每月最后一天的23点59分59秒
		List<OperatorRechargeDataDto>  list = null;
		try {
			list = mapper.queruyRechargeData(orderId, startTime, endTime);
		} catch (Exception e) {
			logger.error("----【容易推】根据orderId:{}获取短信记录信息异常：{}----",orderId,e.getMessage());
		}
		logger.info("----【容易推】根据orderId:{},startTime:{},lastTime:{},查询bw_ryt_payment_info表获取充值记录信息,结果为：{}",orderId,startTime,endTime,list == null ?"空":list.size());
		if(null !=list&&list.size()>0){
			return list;
		} else {
			return new ArrayList<OperatorRechargeDataDto>();
		}

	}

	@Override
	public List<OperatorMsgDataDto> getMsgDataList(Long borrowerId, Long orderId) {
		logger.info("----【容易推】----根据orderId:{}获取短信记录信息<<<<<",orderId);
		int months = 6;
		Calendar calendar = Calendar.getInstance(); 
		List<OperatorMsgDataDto> list = new ArrayList<OperatorMsgDataDto>();
		try {
			while(months > 0){
				String date = DateUtil.getDateString(calendar.getTime(), "yyyy-MM");
				OperatorMsgDataDto dto = this.mapper.queryMsgCount(orderId, date);
				if(null != dto&&dto.getTotalSize() >0 ){
					calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
					//每月开始第一天时间
					String startTime = DateUtil.getDateString(calendar.getTime(), "yyyy-MM-dd") + " 00:00:00";
					calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
					//每月结束最后一天时间
					String endTime = DateUtil.getDateString(calendar.getTime(), "yyyy-MM-dd") + " 23:59:59";
					List<Map<String, Object>> msgrecord = this.mapper.queryMsgData(orderId, startTime, endTime);
					logger.info("----【容易推】----根据orderId:{},startTime:{},endTime:{},查询bw_ryt_sms_info表获取短信记录信息,结果为：{}",orderId,startTime,endTime,msgrecord == null ?"空":msgrecord.size());
					if(null != msgrecord&&msgrecord.size()>0){
						dto.setItems(msgrecord);
						list.add(dto);
					}
				}
				calendar.add(Calendar.MONTH, -1);
				months--;
			}
		} catch (Exception e) {
			logger.error("----【容易推】----根据orderId:{}获取短信记录信息异常：{}----",orderId,e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<OperatorTelDataDto> getTelDataList(Long borrowerId, Long orderId) {
		logger.info("----【容易推】----根据orderId:{}获取通话记录信息----",orderId);
		int months = 6;
		Calendar calendar = Calendar.getInstance();
		List<OperatorTelDataDto> list = new ArrayList<OperatorTelDataDto>();
		try {
			while(months>0){
				String endTime = DateUtil.getDateString(calendar.getTime(), "yyyy-MM");
				OperatorTelDataDto dto = this.mapper.queryCallCount(orderId,endTime);
				if(null !=dto&&dto.getTotalSize()>0){
					calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
					String startTime = DateUtil.getDateString(calendar.getTime(), "yyyy-MM-dd")+ " 00:00:00";
					calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
					String endTimedData = DateUtil.getDateString(calendar.getTime(), "yyyy-MM-dd")+ " 23:59:59";
					List<Map<String, Object>> items = this.mapper.queryCallData(orderId, startTime, endTimedData);
					logger.info("----【容易推】----根据orderId:{},startTime:{},endTime:{},查询bw_third_operate_voice表获取通话记录信息,结果为：{}",orderId,startTime,endTime,items == null ?"空":items.size());
					if(null !=items&&items.size()>0){
						dto.setItems(items);
						list.add(dto);
					}
				}
				months--;
				calendar.add(Calendar.MONTH, -1);
			}
		} catch (Exception e) {
			logger.error("----【容易推】----根据orderId:{}获取通话记录信息异常：{}----",orderId,e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<OperatorBillDataDto> getBillDataList(Long borrowerId,
			Long orderId) {
		logger.info("----【容易推】----根据orderId:{}获取账单记录信息----",orderId);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		try {
			String endTime = DateUtil.getDateString(calendar.getTime(), "yyyy-MM");
			calendar.add(Calendar.MONTH, -5);//时间向后推移5个月
			String startTime = DateUtil.getDateString(calendar.getTime(), "yyyy-MM");
			List<OperatorBillDataDto>  list = this.mapper.queryBillData(orderId, startTime, endTime);
			logger.info("----【容易推】----根据orderId:{},startTime:{},endTime:{},查询bw_ryt_bill_info表获取账单记录信息,结果为：{}",orderId,startTime,endTime,list == null ?"空":list.size());
			if(null != list&&list.size()>0){
				return list;
			} else {
				return new ArrayList<OperatorBillDataDto>();
			}
		} catch (Exception e) {
			logger.error("----【容易推】----根据orderId:{}获取账单记录信息异常：{}----",orderId,e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OperatorFamilyDataDto> getFamilyDataList(Long borrowerId,
			Long orderId) {

		return new ArrayList<OperatorFamilyDataDto>();
		
	}

	@Override
	public List<OperatorNetLogDataDto> getNetLogDataList(Long borrowerId,
			Long orderId) {
		
		return new ArrayList<OperatorNetLogDataDto>();
		
	}

	@Override
	public OperatorMonthInfoDto getMonthinfo(Long borrowerId, Long orderId) {

		return new OperatorMonthInfoDto();
		
	}

	@Override
	public List<OperatorNetDataDto> getNetDataList(Long borrowerId, Long orderId) {
		
		return new ArrayList<OperatorNetDataDto>();
	}
}
