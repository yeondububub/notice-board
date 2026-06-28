package notice.board.article.repository;

import notice.board.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    @Query(
            value = "SELECT article.article_id, article.title, article.content, article.board_id, article.writer_id, " +
                    "article.created_at, article.modified_at " +
                    "FROM (" +
                    "   SELECT article_id from article " +
                    "   WHERE board_id = :boardId " +
                    "   ORDER BY article_id desc " +
                    "   LIMIT :limit OFFSET :offset" +
                    ") t LEFT JOIN article ON t.article_id = article.article_id",
            nativeQuery = true
    )
    List<Article> findAll(
            @Param("boardId") Long boardId,
            @Param("offset") Long offset,
            @Param("limit") Long limit
    );

    @Query(
            value = "SELECT count(*) FROM (" +
                    "   SELECT article_id FROM article " +
                    "   WHERE board_id = :boardId " +
                    "   LIMIT :limit " +
                    ") t",
            nativeQuery = true
    )
    Long count(@Param("boardId") Long boardId, @Param("limit") Long limit);
}
