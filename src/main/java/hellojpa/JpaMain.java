package hellojpa;

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
            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            // 회원 저장
            Member member = new Member();
            member.setUsername("member1");
            // 연관관계의 주인에 값 설정
            member.changeTeam(team);  // ** member를 기준으로 team을 넣음.
            em.persist(member);

//            team.addMember(member);  // ** team을 기준으로 member를 넣음.

            em.flush();
            em.clear();

//            // 조회
//            Member findMember = em.find(Member.class, member.getId());
//            List<Member> members = findMember.getTeam().getMembers();
//
//            for (Member m : members) {
//                System.out.println("m = " + m.getUsername());
//            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
