package nbu.logistic.company.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    @GeneratedValue(strategy = AUTO)
    Long id;
    String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "api_user_roles",
            joinColumns = {@JoinColumn(name = "api_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id")}
    )
    Set<ApiUser> apiUsers = new HashSet<>();
}
