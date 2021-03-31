package hellojpa;

import javassist.compiler.ast.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import java.util.List;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 객체를 생성한 상태(비영속)
            Member2 member = new Member2();
            member.setId(101L);
            member.setName("회원1");

            // 객체를 저장한 상태(영속)
            System.out.println("===Before==="); // DB에 저장안됨
            em.persist(member);
            System.out.println("===After==="); // DB에 저장안됨

            Member2 findMember = em.find(Member2.class, 101L);


//            // 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
//            em.detach(member);
//            // 객체를 삭제한 상태(삭제)
//            em.remove(member);

            tx.commit();    // 여기서 DB에 저장
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
