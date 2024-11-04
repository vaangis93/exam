package dat.entities;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "roles")
@ToString
@Getter
@Setter
// Serialize the class. that means that the class can be converted to a sequence of bytes. so we ensure that the data is the same/correct on both end.
public class Role implements Serializable {

    // A serializable class must have a serial version UID
    @Serial
    private static final long serialVersionUID = 1L;

//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false, unique = true)
//    private int id;

    @Id
    @Column(name = "name", length = 50)
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles" )
    private Set<User> users = new HashSet<>();

    public Role() {}

    public Role(String roleName) {
        this.name = roleName;
    }

    public String getRoleName() {
        return name;
    }

//    public Set<User> getUsers() {
//        return getUsers();
//    }





}
