package openag.jlb;

import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.web.server.ServerWebExchange;

/**
 * {@link GatewayPredicate} that accepts request to the matching instance
 */
public class FolderRoutingGatewayPredicate implements GatewayPredicate {
  private final JenkinsRoutePredicateFactory.Config config;

  private final RequestMapper requestMapper;

  public FolderRoutingGatewayPredicate(JenkinsRoutePredicateFactory.Config config,
                                       RequestMapper requestMapper) {
    this.config = config;
    this.requestMapper = requestMapper;
  }

  @Override
  public boolean test(ServerWebExchange ex) {
    final String instance = this.requestMapper.matchRequestToInstance(ex.getRequest());
    return this.config.getInstance().equals(instance);
  }
}
