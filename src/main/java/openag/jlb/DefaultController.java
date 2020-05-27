package openag.jlb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

  @GetMapping("/jenkins")
  public String customDashboard() {
    return "Hello, this is custom gateway dashboard for Jenkins instances!";
  }

}
