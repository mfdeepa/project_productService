package agg.deepa.productservices.inheritanceexample.joinedtable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JTMentorRepository extends JpaRepository<Mentor,Long > {
    Mentor save(Mentor mentee);
    Mentor findMentorsById(Long id);
}
