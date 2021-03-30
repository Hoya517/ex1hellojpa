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

        EntityManager em = emf.createEntityManager();  // 쉽게 보면 DB CONNECTION

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //insert
//            Member2 member = new Member2();
//            member.setName(3L);
//            member.setName("HelloC");
//            em.persist(member);

            //select
//            Member2 findMember = em.find(Member2.class, 1L);
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getName());

            //delete
//            Member2 findMember = em.find(Member2.class, 1L);
//            em.remove(findMember);

            // update
//            Member2 findMember = em.find(Member2.class, 1L);
//            findMember.setName("HelloJPA");

            //JPQL
            List<Member2> result = em.createQuery("select m from Member2 as m", Member2.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member2 member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();  // 문제발생 안하면 커밋
        } catch (Exception e) {
            tx.rollback();  // 문제발생하면 롤백
        } finally {
            em.close();  // EntityManager가 꼭 닫혀야 함!
        }
        emf.close();
    }
}
