package notice.board.comment.repository;

import notice.board.comment.entity.CommentV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepositoryV2 extends JpaRepository<CommentV2, Long> {

    @Query("SELECT c FROM CommentV2 c " +
            "WHERE c.articleId = :articleId " +
            "   and c.commentPath.path = :path"
    )
    Optional<CommentV2> findByArticleIdAndPath(
        @Param("articleId") Long articleId,
        @Param("path") String path
);

    @Query(
            value = "select path from comment_v2 " +
                    "where article_id = :articleId and path > :pathPrefix and path like :pathPrefix% " +
                    "order by path desc limit 1",
            nativeQuery = true
    )
    Optional<String> findDescendantsTopPath(
            @Param("articleId") Long articleId,
            @Param("pathPrefix") String pathPrefix
    );
}
