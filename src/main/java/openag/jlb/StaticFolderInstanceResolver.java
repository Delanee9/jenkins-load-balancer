package openag.jlb;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 'Dummy' instance resolver based on the hard-coded mapping. More sophisticated implementations can use database/api as
 * the source for mapping
 */
@Component
public class StaticFolderInstanceResolver implements FolderInstanceResolver {

  /* key -> folder name; value -> instance ID */
  private final Map<String, String> mapping = new HashMap<>();

  public StaticFolderInstanceResolver() {
    mapping.put("P1", "instance1");
    mapping.put("P2", "instance2");
    // add more if needed....
  }

  @Override
  public String instanceFor(String folder) {
    return mapping.get(folder);
  }
}
