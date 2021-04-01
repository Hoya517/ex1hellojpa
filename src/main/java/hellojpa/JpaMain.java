package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

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
            member.setTeam(team);
            em.persist(member);

            // 영속성 컨텍스트에 1차 캐시 때문에 qeury 안보임.
            // DB 쿼리문 보고싶어!!
            em.flush();  // 영속성 컨텍스트에 있는거 db에 쿼리를 다 날리고 싱크를 맞춤.
            em.clear();  // 영속성 컨텍스트 초기화.

            //조회 
            Member findMember = em.find(Member.class, member.getId());

            //참조를 사용해서 연관관계 조회
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            /*
            * 새로운 팀으로 바꾸고 싶어? (연관관계 수정)
            // 새로운 팀B 
            Team teamB = new Team(); 
            teamB.setName("TeamB"); 
            em.persist(teamB); 

            // 회원1에 새로운 팀B 설정 
            member.setTeam(teamB); 
            */

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
