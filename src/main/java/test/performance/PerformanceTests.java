package test.performance;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import static framewise.dustview.SimpleDustTemplateView.*;

/**
 * Created by chanwook on 2014. 1. 22..
 */
@Controller
public class PerformanceTests {

    public String getProduct(ModelMap model){

        model.put(CONTENT_TEXT_KEY, "{json}");
        model.put(TEMPLATE_KEY, "templateKey");
        model.put(VIEW_PATH_OVERRIDE, "http://image.gsshop.com/../markup.js");

        return "performance";
    }
}
