package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();  // 쉽게 보면 JDBC 드라이버

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member2 member = new Member2();
            member.setId(2L);
            member.setName("helloB");

            em.persist(member);  // 문제 발생?

            tx.commit();  // 문제발생 안하면 커밋
        } catch (Exception e) {
            tx.rollback();  // 문제발생하면 롤백
        } finally {
            em.close();  // EntityManager가 꼭 닫혀야 함!
        }
        emf.close();
    }
}
