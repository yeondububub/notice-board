package notice.board.articleread.service.event.handler;

import lombok.RequiredArgsConstructor;
import notice.board.articleread.repository.ArticleQueryModelRepository;
import notice.board.common.event.Event;
import notice.board.common.event.EventType;
import notice.board.common.event.payload.ArticleDeletedEventPayload;
import notice.board.common.event.payload.CommentCreatedEventPayload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentCreatedEventHandler implements EventHandler<CommentCreatedEventPayload> {

    private final ArticleQueryModelRepository articleQueryModelRepository;

    @Override
    public void handle(Event<CommentCreatedEventPayload> event) {
        articleQueryModelRepository.read(event.getPayload().getArticleId())
                .ifPresent(articleQueryModel -> {
                    articleQueryModel.updateBy(event.getPayload());
                    articleQueryModelRepository.update(articleQueryModel);
                });
    }

    @Override
    public boolean supports(Event<CommentCreatedEventPayload> event) {
        return EventType.COMMENT_CREATED == event.getType();
    }
}
