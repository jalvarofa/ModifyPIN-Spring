package es.payments.bankapp.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class ControllerTests {

    @Test
    public void testHandleRequestView() throws Exception{		
        CardController controller = new CardController();
        ModelAndView modelAndView = controller.handleRequest(null, null);		
        assertEquals("modifyPIN", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        String nowValue = (String) modelAndView.getModel().get("now");
        assertNotNull(nowValue);
    }

}
