package com.kidzoo.util;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import com.kidzoo.bean.KidZooRestResponse;
import com.kidzoo.bean.Toy;
import com.kidzoo.constant.RestResponseCode;

public class KidZooRestUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(KidZooRestUtil.class);

	private KidZooRestUtil() {
	}
	
	public static KidZooRestResponse createResponse(List<Toy> response) {
		LOGGER.info("Creating Response {} ", response);
		KidZooRestResponse restResponse = new KidZooRestResponse();
		restResponse.setStatusCode(RestResponseCode.SUCCESS.getCode());
		restResponse.setStatus(RestResponseCode.SUCCESS.name());
		restResponse.setMessage(HttpStatus.OK.name());
		restResponse.setData(response);
		return restResponse;
	}

}
