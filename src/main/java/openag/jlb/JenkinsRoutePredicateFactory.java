package openag.jlb;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Spring Cloud Gateway extension that enables 'Jenkins' predicate configuration
 */
@Component
public class JenkinsRoutePredicateFactory extends AbstractRoutePredicateFactory<JenkinsRoutePredicateFactory.Config> {

  private final RequestMapper requestMapper;

  public JenkinsRoutePredicateFactory(RequestMapper requestMapper) {
    super(Config.class);
    this.requestMapper = requestMapper;
  }

  @Override
  public Predicate<ServerWebExchange> apply(Config config) {
    return new FolderRoutingGatewayPredicate(config, requestMapper);
  }

  @Override
  public List<String> shortcutFieldOrder() {
    return Collections.singletonList("instance");
  }

  public static class Config {
    private String instance;

    public String getInstance() {
      return instance;
    }

    public void setInstance(String instance) {
      this.instance = instance;
    }
  }
}
