package seedu.address.ui;

import static seedu.address.model.person.ProfilePicture.DEFAULT_PICTURE;
import static seedu.address.model.person.ProfilePicture.getPath;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final ReadOnlyPerson person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label appointment;
    @FXML
    private ImageView profilePicture;
    @FXML
    private FlowPane groups;
    @FXML
    private FlowPane tags;

    public PersonCard(ReadOnlyPerson person, int displayedIndex) {
        super(FXML);
        groups.setHgap(5);
        this.person = person;
        id.setText(displayedIndex + ". ");
        initTags(person);
        initGroups(person);
        initPicture(person);
        bindListeners(person);
    }

    /**
     * Binds the individual UI elements to observe their respective {@code Person} properties
     * so that they will be notified of any changes.
     */
    private void bindListeners(ReadOnlyPerson person) {
        name.textProperty().bind(Bindings.convert(person.nameProperty()));
        phone.textProperty().bind(Bindings.convert(person.phoneProperty()));
        address.textProperty().bind(Bindings.convert(person.addressProperty()));
        email.textProperty().bind(Bindings.convert(person.emailProperty()));
        appointment.textProperty().bind(Bindings.convert(person.appointmentProperty()));
        person.profilePictureProperty().addListener(((observable, oldValue, newValue) -> initPicture(person)));
        person.tagProperty().addListener((observable, oldValue, newValue) -> {
            tags.getChildren().clear();
            person.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        });
        //@@author arturs68
        person.groupProperty().addListener((observable, oldValue, newValue) -> {
            groups.getChildren().clear();
            person.getGroups().forEach(group -> groups.getChildren().add((new Label(group.groupName))));
        });
        //@@author
    }

    private void initTags(ReadOnlyPerson person) {
        person.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    //@@author arturs68
    /**
     * Initializes the group labels and sets style to them
     *
     * If the path of the image is valid, initializes the image. Otherwise leaves the picture blank.
     */
    private void initPicture(ReadOnlyPerson person) {
        Image im;
        try {
            im = new Image(getPath(person.getProfilePicture().value));
        } catch (IllegalArgumentException iae) {
            im = new Image(getPath(DEFAULT_PICTURE));
        }
        profilePicture.setPreserveRatio(false);
        profilePicture.setImage(im);
        profilePicture.setFitWidth(90);
        profilePicture.setFitHeight(120);
    }

    /**
     * Initializes the group labels and sets style to them
     */
    private void initGroups(ReadOnlyPerson person) {
        person.getGroups().forEach(group -> groups.getChildren().add(new Label(group.groupName)));
        groups.getChildren()
                .forEach(label -> label
                        .setStyle("-fx-background-color: mediumblue;"
                                + "-fx-effect: dropshadow( one-pass-box , gray , 8 , 0.0 , 2 , 0 );"));
    }
    //@@author

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
