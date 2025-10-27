package org.usman.api.college.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usman.api.college.repository.AuditLogRepository;
import org.usman.api.college.repository.entities.AuditLog;
import org.usman.api.college.repository.entities.StudentAdmission;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl {

    private final AuditLogRepository auditLogRepository;

    public void saveAuditLog(StudentAdmission enrollment, String action) {
        log.info("*** \nCreate audit Log with Propagation.REQUIRES_NEW ");
        AuditLog auditLog = new AuditLog();
        //auditLog.setOrderId(enrollment.getAdmissionId());
        auditLog.setAction(action);
        auditLog.setJsonData(convertPojoToString(enrollment));
        auditLog.setCreatedAt(LocalDateTime.now());
        auditLog.setCreatedBy("usman");

        auditLogRepository.save(auditLog);
    }

    private String convertPojoToString(Object object){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());//to handle to jsr java 8 error

            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Json conversion error for pojo:{}", object);
            throw new RuntimeException(e);
        }
    }
}
