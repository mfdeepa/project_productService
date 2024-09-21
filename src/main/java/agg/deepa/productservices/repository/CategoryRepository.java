package agg.deepa.productservices.repository;

import agg.deepa.productservices.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);
    List<Category> findAllByIdIn(List<Long> ids);
}
