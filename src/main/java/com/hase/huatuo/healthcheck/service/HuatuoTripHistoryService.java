package com.hase.huatuo.healthcheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.request.TripHistoryReqBody;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.model.response.TripHistoryRespBody;

@Service
public class HuatuoTripHistoryService {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private int limit = 30;
	
	public static final String TRIP_HISTORY_SQL = "SELECT serail_number,report_date from trip_survey_hacn where staff_id=? order by report_date desc limit 0, ?";

	
	public ResponseEntity getTripHistory(final TripHistoryReqBody tripHistoryReqBody) {
		
		CommonResponse result = new CommonResponse();
		try { 
			RowMapper<TripHistoryRespBody> tripHistoryRespBody = BeanPropertyRowMapper.newInstance(TripHistoryRespBody.class);
			List<TripHistoryRespBody> response = jdbcTemplate.query(TRIP_HISTORY_SQL, tripHistoryRespBody, new Object[] {tripHistoryReqBody.getStaffId(), limit});
			result.setCode("200");
			result.setMsg("success");
			result.setReturnObject(response);
		} catch (EmptyResultDataAccessException e) { 
			e.printStackTrace();
			result.setCode("200");
			result.setMsg("success");
			result.setReturnObject(null);
		} catch (Exception e) { 
			ErrorHandleHelper.getInstance().throwBadRequestRestException("DB_ERROR", "GET HISTORY ERROR", tripHistoryReqBody.getStaffId());
		}
		return ResponseEntity.ok(result);
	}
}
