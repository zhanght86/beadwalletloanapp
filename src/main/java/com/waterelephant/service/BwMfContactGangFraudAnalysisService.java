package com.waterelephant.service;

import com.alibaba.fastjson.JSONObject;

public interface BwMfContactGangFraudAnalysisService {

	void saveContactGangFraudAnalysis(String taskId, Long orderId, JSONObject reportData);

}
