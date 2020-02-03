package com.hase.huatuo.healthcheck.helper;

import org.junit.Assert;
import org.junit.Test;

import com.hase.huatuo.healthcheck.error.exception.BadRequestRestException;
import com.hase.huatuo.healthcheck.error.exception.TechnicalRestException;

public class ErrorHandleHelperTest {

	@Test
	public void testThrowTechnicalRestException() {
		try {
			ErrorHandleHelper.getInstance().throwTechnicalRestException("Server Error", "read DB exception", null);
		} catch (TechnicalRestException ex) {
			Assert.assertEquals("Server Error", ex.getErrorInfo().getCode());
			Assert.assertEquals("read DB exception", ex.getErrorInfo().getMessage().getKey());
			
		}
	}

	@Test
	public void testThrowBadRequestRestException() {
		try {
			ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "staff ID is error", null);
		} catch (BadRequestRestException ex) {
			Assert.assertEquals("Bad Request", ex.getErrorInfo().getCode());
			Assert.assertEquals("staff ID is error", ex.getErrorInfo().getMessage().getKey());
		}
		
	}

}
