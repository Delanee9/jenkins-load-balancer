package openag.jlb;

/**
 * Determines the destination jenkins instance for the folder
 */
public interface FolderInstanceResolver {

  String instanceFor(String folder);
}
