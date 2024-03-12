package entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String fio;

    @Enumerated(EnumType.STRING)
    Status status;

    @OneToMany(mappedBy = "responsible")
    private List<Request> requests;

    public int getTotalRequests() {
        return requests != null ? requests.size() : 0;
    }

    public void addRequest(Request request) {
        requests.add(request);
        request.setResponsible(this);
    }

}
