package notice.board.view.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import notice.board.common.event.EventType;
import notice.board.common.event.payload.ArticleViewedEventPayload;
import notice.board.common.outboxmessagerelay.OutboxEventPublisher;
import notice.board.view.entity.ArticleViewCount;
import notice.board.view.repository.ArticleViewCountBackUpRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleViewCountBackUpProcessor {

    private final OutboxEventPublisher outboxEventPublisher;
    private final ArticleViewCountBackUpRepository articleViewCountBackUpRepository;

    @Transactional
    public void backUp(Long articleId, Long viewCount) {
        int result = articleViewCountBackUpRepository.updateViewCount(articleId, viewCount);
        if (result == 0) {
            articleViewCountBackUpRepository.findById(articleId)
                    .ifPresentOrElse(ignored -> {
                            },
                            () -> articleViewCountBackUpRepository
                                    .save(ArticleViewCount.init(articleId, viewCount))
                    );
        }

        outboxEventPublisher.publish(
                EventType.ARTICLE_VIEWED,
                ArticleViewedEventPayload.builder()
                        .articleId(articleId)
                        .articleViewCount(viewCount)
                        .build(),
                articleId
        );
    }
}
