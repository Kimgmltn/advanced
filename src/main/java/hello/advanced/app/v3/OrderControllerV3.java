package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderServiceV2;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(@RequestParam(name="itemId") String itemId){

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderServiceV2.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
