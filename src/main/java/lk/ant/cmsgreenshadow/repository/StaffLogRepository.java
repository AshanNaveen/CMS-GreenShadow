package lk.ant.cmsgreenshadow.repository;

import lk.ant.cmsgreenshadow.entity.StaffLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Naveen Theekshana
 * @date 12/1/2024
 * @project CMSGreenShadow
 */
@Repository
public interface StaffLogRepository extends JpaRepository<StaffLogEntity,String> {
    void deleteByLog_LogId(String logLogId);
    List<StaffLogEntity> findByLog_LogId(String logLogId);

}
