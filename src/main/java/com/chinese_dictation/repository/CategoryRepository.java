package com.chinese_dictation.repository;

import com.chinese_dictation.model.dto.response.CategoryStatisticsRespoonse;
import com.chinese_dictation.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT new com.chinese_dictation.model.dto.response.CategoryStatisticsRespoonse(" +
            "c.id, " +
            "c.name, " +
            "c.description, " +
            "c.imageUrl, " +
            "c.isActive, " +
            "COUNT(DISTINCT l.id), " +
            "COUNT(DISTINCT up.user.id), " +
            "COUNT(up.id)) " +
            "FROM Category c " +
            "LEFT JOIN c.lessons l " +
            "LEFT JOIN UserProgress up ON up.lesson.id = l.id " +
            "WHERE c.isActive = true " +
            "GROUP BY c.id, c.name, c.description, c.imageUrl, c.isActive " +
            "ORDER BY c.name")
    List<CategoryStatisticsRespoonse> findCategoryStatistics();
}
