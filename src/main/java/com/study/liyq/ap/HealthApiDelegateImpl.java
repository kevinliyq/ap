package com.study.liyq.ap;

import com.study.liyq.ap.api.HealthApiDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HealthApiDelegateImpl implements HealthApiDelegate {
    private Logger logger = LoggerFactory.getLogger(HealthApiDelegateImpl.class);
    @Override
    public ResponseEntity<Void> getHealth() {
        logger.info("getHealth is called");

        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
