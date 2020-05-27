package openag.jlb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RequestMapper {
  private static final Logger log = LoggerFactory.getLogger(RequestMapper.class);

  // matches typical jenkins job url prefix, ex. /jenkins/job/P2/job/j2/
  private static final Pattern PATH_PATTERN = Pattern.compile("/jenkins/job/(.+?)/.*");

  private final FolderInstanceResolver instanceResolver;

  public RequestMapper(FolderInstanceResolver instanceResolver) {
    this.instanceResolver = instanceResolver;
  }

  /**
   * Attempts to match incoming request to one of the instances available in config.
   *
   * @return instance ID if request could be matched; NULL otherwise
   */
  public String matchRequestToInstance(ServerHttpRequest request) {
    final String path = request.getPath().value();
    String instance = matchInstance(path);
    if (instance != null) {
      log.info("Path {} matched to instance {}", path, instance);
      return instance;
    }

    final String referer = request.getHeaders().getFirst("Referer");
    instance = matchInstance(referer);
    if (instance != null) {
      log.info("Referer {} matched to instance {}", referer, instance);
      return instance;
    }

    log.error("UNMATCHED PATH: {}", path);
    return null;
  }

  private String matchInstance(String path) {
    final Matcher matcher = PATH_PATTERN.matcher(path);

    if (matcher.find()) {
      final String folder = matcher.group(1);
      return instanceResolver.instanceFor(folder);
    }
    return null;
  }
}
