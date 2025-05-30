package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepositoryV1;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepositoryV1, LogTrace logTrace) {
        this.orderRepositoryV1 = orderRepositoryV1;
        this.template = new TraceTemplate(logTrace);
    }

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", () -> {
            orderRepositoryV1.save(itemId);
            return null;
        });
    }
}
