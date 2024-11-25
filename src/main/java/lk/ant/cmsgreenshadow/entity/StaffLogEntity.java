package lk.ant.cmsgreenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "staff_log")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffLogEntity implements Serializable {
    @Id
    private String staffLogId;
    @ManyToOne
    @JoinColumn(name = "staff_id" ,referencedColumnName = "staffId")
    private StaffEntity staffEntity;
    @ManyToOne
    @JoinColumn(name = "log_id", referencedColumnName = "logId")
    private LogEntity logEntity;
}
