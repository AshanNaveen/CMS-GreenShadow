package lk.ant.cmsgreenshadow.service;

import lk.ant.cmsgreenshadow.dto.LogDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 11/24/2024
 * @project CMSGreenShadow
 */
@Service
public interface LogService {
    void saveLog(LogDto log);
    void updateLog(String id, LogDto log);
    LogDto searchLog(String id);
    boolean deleteLog(String id);
    List<LogDto> getAllLogs();
}
