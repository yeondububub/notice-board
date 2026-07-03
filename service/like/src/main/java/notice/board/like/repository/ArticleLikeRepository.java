package notice.board.like.repository;

import notice.board.like.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {

    Optional<ArticleLike> findByArticleIdAndUserId(Long articleId, Long userId);

    @Modifying
    @Query("DELETE FROM ArticleLike " +
            "WHERE ArticleLike.articleId = :articleId AND ArticleLike.userId = :userId")
    int deleteByArticleIdAndUserId(@Param("articleId") Long articleId, @Param("userId") Long userId);
}
