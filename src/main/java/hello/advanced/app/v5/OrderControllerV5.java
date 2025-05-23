package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {
    
    private final OrderServiceV5 orderServiceV2;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderServiceV2, LogTrace trace) {
        this.orderServiceV2 = orderServiceV2;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(@RequestParam(name="itemId") String itemId){
        return template.execute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderServiceV2.orderItem(itemId);
                return "ok";
            }
        });
    }

}
