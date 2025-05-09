package rpg;

/**
 * Classes that are both inspectable and interactable.
 */
public interface Actionable extends Inspectable, Interactable {
    /**
     * Gets the description, I know it says it isn't being used but I'm pretty sure it is...
     *
     * @return String description
     */
    @SuppressWarnings("unused")
    String getDescription();
}
