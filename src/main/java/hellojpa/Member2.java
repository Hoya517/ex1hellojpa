package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Member2 {

    @Id
    private Long id;

    @Column(unique = true, length = 10)
    private String name;

    // JPA에서 생성자를 사용하려면 기본생성자는 무조건 있어야함.
    public Member2() {

    }

    public Member2(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
