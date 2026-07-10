package notice.board.like.repository;

import jakarta.persistence.LockModeType;
import notice.board.like.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
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
            "WHERE articleId = :articleId AND userId = :userId")
    int deleteByArticleIdAndUserId(@Param("articleId") Long articleId, @Param("userId") Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT al FROM ArticleLike al WHERE al.articleId = :articleId AND al.userId = :userId")
    Optional<ArticleLike> findLockedByArticleIdAndUserId(@Param("articleId") Long articleId, @Param("userId") Long userId);
}
