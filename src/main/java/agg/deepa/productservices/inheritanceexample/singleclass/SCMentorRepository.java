package agg.deepa.productservices.inheritanceexample.singleclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SCMentorRepository extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentor);
    Mentor findMentorsById(Long id);

}
