package uz.pdp.appnews.entity.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import uz.pdp.appnews.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(updatable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @JoinColumn(updatable = false)
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;



}
