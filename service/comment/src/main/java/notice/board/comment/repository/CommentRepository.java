package notice.board.comment.repository;

import notice.board.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query (
            value = "SELECT count(*) FROM ( " +
                    "   SELECT comment_id " +
                    "   FROM COMMENT " +
                    "   WHERE article_id = :articleId AND parent_comment_id = :parentCommentId " +
                    "   LIMIT :limit" +
                    ") t",
            nativeQuery = true
    )
    Long countBy(
            @Param("articleId") Long articleId,
            @Param("parentCommentId") Long parentCommentId,
            @Param("limit") Long limit
    );
}
