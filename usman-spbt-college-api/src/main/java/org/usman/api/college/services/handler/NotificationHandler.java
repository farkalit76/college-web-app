package org.usman.api.college.services.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.usman.api.college.repository.entities.StudentAdmission;

/**
 * Implementation Note : This class is an....
 *
 * @author UsmanFarkalit
 * @date 10-07-2025
 * @since 1.0
 */

@Slf4j
@Service
public class NotificationHandler {


    @Transactional(propagation = Propagation.NEVER)
    public void sendOrderConfirmationNotification(StudentAdmission enrollment){
        log.info("*** \nSend order confirmation Notification with Propagation.NEVER ");
        log.info("Order placed successfully, notification for enrollment :{}", enrollment);
    }
}
