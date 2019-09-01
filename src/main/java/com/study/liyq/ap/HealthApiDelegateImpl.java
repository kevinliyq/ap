package com.study.liyq.ap;

import com.study.liyq.ap.api.HealthApiDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HealthApiDelegateImpl implements HealthApiDelegate {
    @Override
    public ResponseEntity<Void> getHealth() {
        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
