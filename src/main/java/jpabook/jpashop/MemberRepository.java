package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    // JPA 를 사용하기 위한 어노테이션 및 의존선 주입
    @PersistenceContext
    private EntityManager em;

    // 유저 저장
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    // 유저 단일 조회
    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
