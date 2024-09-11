package agg.deepa.productservices.inheritanceexample.mapppedtable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MSMentorRepository extends JpaRepository<Mentor, Long> {
    Mentor save(Mentor mentee);
    Mentor findMentorsById(Long id);
}
