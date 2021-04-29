package uz.pdp.appnews.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PRIVATE_MEMBER;
import uz.pdp.appnews.entity.enums.Permissions;
import uz.pdp.appnews.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor @NoArgsConstructor @Entity
public class Role extends AbsEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @ElementCollection() @Enumerated(value = EnumType.STRING)
    private List<Permissions> permissionsList;

    @Column(length = 500)
    private String description;
}
