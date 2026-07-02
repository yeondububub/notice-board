package notice.board.like.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import notice.board.common.snowflake.Snowflake;
import notice.board.like.entity.ArticleLike;
import notice.board.like.repository.ArticleLikeRepository;
import notice.board.like.service.response.ArticleLikeResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleLikeService {

    private final Snowflake snowflake = new Snowflake();
    private final ArticleLikeRepository articleLikeRepository;

    public ArticleLikeResponse read(Long articleId, Long userId) {
        return articleLikeRepository.findByArticleIdAndUserId(articleId, userId)
                .map(ArticleLikeResponse::from)
                .orElseThrow();
    }

    @Transactional
    public void like(Long articleId, Long userId) {
        ArticleLike articleLike = articleLikeRepository.save(
                ArticleLike.create(
                        snowflake.nextId(),
                        articleId,
                        userId
                )
        );
    }

    @Transactional
    public void unlike(Long articleId, Long userId) {
        articleLikeRepository.findByArticleIdAndUserId(articleId, userId)
                .ifPresent(articleLikeRepository::delete);
    }
}
